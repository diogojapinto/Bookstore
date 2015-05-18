/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;

/**
 *
 * @author diogo
 */
@Singleton
public class StoreStorage {
    
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
    
    public int addOrder(Order order) {
        String title = order.getTitle();
        int quantity = order.getQuantity();
        String clientName = order.getClientName();
        String clientEmail = order.getClientEmail();
        Order newOrder = new Order(title, quantity, clientName, clientEmail, 
                "waiting expedition");
        orders.putIfAbsent(clientName, new ArrayList<>());
        orders.get(clientName).add(order);
        return newOrder.getId();
    }
}
