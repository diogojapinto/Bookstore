/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Request {
    private String title;
    private int quantity;

    public Request() {
        title = null;
        quantity = -1;
    }

    public Request(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }
    
    public Request(String message) {
        String[] elems = message.split(" ");
        title = elems[0];
        quantity = Integer.parseInt(elems[1]);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return quantity;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }
    
    public String toMessage() {
        return title + " " + quantity;
    }
    
}
