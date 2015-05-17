/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.StoreSessionRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author diogo
 */
@Path("store")
public class StoreREST {
    StoreSessionRemote storeSession = lookupStoreSessionRemote();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StoreREST
     */
    public StoreREST() {
    }

    /**
     * Retrieves representation of an instance of com.book.store.api.StoreREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of StoreREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    private StoreSessionRemote lookupStoreSessionRemote() {
        try {
            javax.naming.Context c = new InitialContext();
            return (StoreSessionRemote) c.lookup("java:global/BookStore/StoreEJB/StoreSession!com.book.store.StoreSessionRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
