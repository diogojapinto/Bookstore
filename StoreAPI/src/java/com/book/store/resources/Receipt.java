/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Receipt {
    
    private String clientName;
    private String clientEmail;
    private String title;
    private int quantity;
    private float totalPrice;
    private String state;
    
    public Receipt() {
        
    }
    
    public Receipt(Order order) {
        
    }
    
    
    public void printToFile() {
        // TODO
    }
    
    public void sendToEmail() {
        // TODO
    }
}
