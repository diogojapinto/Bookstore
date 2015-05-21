/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui.resources;

import javax.persistence.Basic;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Book {
    private String title;
    private double price;
    @Basic(optional = false)
    private long stock;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, double price, long stock) {
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (title != null ? title.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.book.store.resources.Book[ title=" + title + " ]";
    }
    
}
