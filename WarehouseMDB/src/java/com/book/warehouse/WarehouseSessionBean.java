/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.warehouse;

import com.book.store.Request;
import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.Stateful;

/**
 *
 * @author diogo
 */
@Singleton
public class WarehouseSessionBean implements WarehouseSessionBeanRemote {

    private ArrayList<Request> requests = new ArrayList<>();
    
    @Override
    public void storeRequest(Request request) {
        requests.add(request);
    }
    
    @Override
    public ArrayList<Request> getRequests() {
        return requests;
    }
}
