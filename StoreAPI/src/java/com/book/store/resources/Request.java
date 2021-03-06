/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Request implements Serializable {
    private Integer quantity;
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;

    public Request() {
        title = null;
        quantity = -1;
    }
    
    

    public Request(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }
    
    public Request(String message) {
        String[] elems = message.split("£");
        title = elems[0];
        quantity = Integer.parseInt(elems[1]);
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, String title, int quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.book.warehouse.Request[ id=" + id + " ]";
    }
    
    public String toMessage() {
        return title + "£" + quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
