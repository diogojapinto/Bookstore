/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store;

/**
 *
 * @author diogo
 */
public interface IOrder {

    String getClientEmail();

    String getClientName();

    int getId();

    int getQuantity();

    String getStatus();

    String getTitle();

    void setClientEmail(String clientEmail);

    void setClientName(String clientName);

    void setQuantity(int quantity);

    void setStatus(String status);

    void setTitle(String title);
    
    void printReceipt();
    
    void sendReceiptToEmail();
    
}
