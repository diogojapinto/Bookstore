/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.resources.StoreStorage;
import com.book.store.resources.Order;
import java.net.URI;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author diogo
 */
@Stateless
@Path("/orders")
public class OrdersResource {

    @Context
    private UriInfo context;
    
    @EJB
    private StoreStorage storage;

    /**
     * Creates a new instance of StoreREST
     */
    public OrdersResource() {
    }

    /**
     * Retrieves representation of an instance of com.book.store.api.OrdersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{username}")
    @Produces("application/xml")
    public ArrayList<Order> getOrders(@PathParam("username") String username) {
        return storage.getOrders(username);
    }

    /**
     * PUT method for updating or creating an instance of OrdersResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/xml")
    public Response placeOrder(Order order) {
      storage.addOrder(order);
      
      return Response.ok().build();
    }
}
