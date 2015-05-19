/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.resources.StoreStorage;
import com.book.store.resources.Order;
import com.book.store.resources.Order.StatusType;
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

    public OrdersResource() {
    }
    
    @GET
    public ArrayList<Order> getAllOrders() {
        return storage.getAllOrders();
    }

    @GET
    @Path("{username}")
    @Produces("application/xml")
    public ArrayList<Order> getOrders(@PathParam("username") String username) {
        return storage.getOrders(username);
    }

    @POST
    @Path("{inStore}")
    @Consumes("application/xml")
    public Response placeOrder(Order order,@PathParam("inStore") boolean inStore) {
      StatusType status = storage.addOrder(order, inStore);
      return Response.ok().entity(status.toString()).build();
    }
}
