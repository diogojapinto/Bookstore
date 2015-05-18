/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public interface IBook extends Serializable {

    @Override
    boolean equals(Object object);

    double getPrice();

    int getStoreStock();

    String getTitle();

    int getWarehouseStock();

    @Override
    int hashCode();

    void setPrice(double price);

    void setStoreStock(int storeStock);

    void setTitle(String title);

    void setWarehouseStock(int warehouseStock);

    @Override
    String toString();
    
}
