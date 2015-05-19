/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import com.book.store.resources.Order.StatusType;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author diogo
 */
@Singleton
public class StoreStorage {
    
    @PersistenceContext(unitName = "StoreAPIPU")
    private EntityManager em;
    
    private final HashMap<String, ArrayList<Order>> orders = new HashMap<>();
    
    public StoreStorage() {
    }
    
    public ArrayList<Order> getOrders(String username) {
        if (orders.containsKey(username)) {
            return orders.get(username);
        } else {
            return new ArrayList<>();
        }
    }
    
    public StatusType addOrder(Order receivedOrder, boolean inStore) {
        Order order = new Order(receivedOrder);
        
        String clientName = order.getClientName();
        String title = order.getTitle();
        int quantity = order.getQuantity();
        
        // Verify stock
        Book book = (Book) em.createNamedQuery("Book.findByTitle")
                .setParameter("title", title).getResultList().get(0);
        
        if (book.getStock() > quantity) {
            book.setStock(book.getStock() - quantity);
            em.persist(book);
            
            if (inStore) {
                order.setStatus(Order.getDeliveredStatus());
                order.printReceipt();
            } else {
                order.setStatus(Order.getDispatchedStatus(1));
                order.sendReceiptToEmail();
            }
        } else {
            // TODO: send message to warehouse
            // TODO: create requests
            order.setStatus(Order.getWaitingExpeditionStatus());
            orders.putIfAbsent(clientName, new ArrayList<>());
            orders.get(clientName).add(order);
        }
        
        return Order.getOrderStatusType(order.getStatus());
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allOrders = new ArrayList<>();
        for (ArrayList<Order> items : orders.values()) {
            allOrders.addAll(items);
        }
        return allOrders;
    }
}
