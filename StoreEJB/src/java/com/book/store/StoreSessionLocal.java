/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store;

import javax.ejb.Local;

/**
 *
 * @author diogo
 */
@Local
public interface StoreSessionLocal {

    void businessMethod();
    
}
