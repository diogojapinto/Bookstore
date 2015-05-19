/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.warehouse;

import com.book.store.Request;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author diogo
 */
@Remote
public interface WarehouseSessionBeanRemote {

    void storeRequest(Request request);
    ArrayList<Request> getRequests();
}
