/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Request {
    private String title;
    private int amount;

    public Request() {
        title = null;
        amount = -1;
    }

    public Request(String title, int amount) {
        this.title = title;
        this.amount = amount;
    }
    
    public Request(String message) {
        String[] elems = message.split(" ");
        title = elems[0];
        amount = Integer.parseInt(elems[1]);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String toMessage() {
        return title + " " + amount;
    }
    
}
