/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import com.book.store.IOrder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.DateFormatter;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Order implements IOrder {
    
    @PersistenceContext(unitName = "StoreAPIPU")
    private EntityManager em;
    
    private static int counter = 0;
   
    public enum StatusType {
        DISPATCHED,
        TO_BE_DISPATCHED,
        WAITING_EXPEDITION,
        NONE
    };
    
    public static String getWaitingExpeditionStatus() {
        return "waiting expedition";
    }
    
    public static String getDispatchedStatus() {
        return "dispatched at " + addDaysToNow(0);
    }
    
    public static String getToBeDispatchedStatus(int days) {
        return "dispatch will occur at " + addDaysToNow(days);
    }
    
    public static StatusType getOrderStatusType(String status) {
        if (status.contains("waiting")) {
            return StatusType.WAITING_EXPEDITION;
        } else if (status.contains("dispatched")) {
            return StatusType.DISPATCHED;
        } else if (status.contains("dispatch will occur")) {
            return StatusType.TO_BE_DISPATCHED;
        } else {
            return StatusType.NONE;
        }
    }
    
    private static String addDaysToNow(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, days);
        return sdf.format(c.getTime());
    }
    
    private int id;
    private String title;
    private int quantity;
    private String clientName;
    private String clientEmail;
    private String state;
    
    
    public Order() {
        id = -1;
        title = null;
        quantity = -1;
        clientName = null;
        clientEmail = null;
        state = null;
    }

    public Order(String title, int quantity, String clientName, 
            String clientEmail, String orderState) {
        this.id = ++counter;
        this.title = title;
        this.quantity = quantity;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.state = orderState;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getClientName() {
        return clientName;
    }

    @Override
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String getClientEmail() {
        return clientEmail;
    }

    @Override
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    
    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int getId() {
        return id;
    }
    
    /*
    private int id;
    private String title;
    private int quantity;
    private String clientName;
    private String clientEmail;
    private String state;*/
    
    @Override
    public void printReceipt() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
        String date = sdf.format(new Date());
        
        Path path = Paths.get(System.getProperty("user.home"), "receipts", date + ".txt");
        
        try {
            PrintWriter writer = new PrintWriter(path.toString());
            
            writer.println("Title:\t" + title);
            writer.println("Quantity:\t" + quantity);
            writer.println("Total Cost:\t" + getTotalCost());
            writer.println();
            writer.println("Client:\t" + clientName);
            writer.println("E-mail:\t" + clientEmail);
            writer.println("State:\t" + state);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendReceiptToEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private float getTotalCost() {
        Book book = (Book) em.createNamedQuery("Book.findByTitle")
                .setParameter("title", title).getResultList().get(0);
        return (float) (quantity * book.getPrice());
    }
    
}
