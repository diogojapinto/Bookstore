/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.resources.StoreStorage;
import com.book.store.resources.Order;
import com.book.store.resources.Order.StatusType;
import com.book.store.resources.Request;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author diogo
 */
@Stateless
@Path("orders")
public class OrdersResource {

    @Context
    private UriInfo context;
    @EJB
    private StoreStorage storage;
    @PersistenceContext(unitName = "StoreAPIPU")
    private EntityManager em;

    public OrdersResource() {
    }

    @GET
    @Produces("application/xml")
    public ArrayList<Order> getAllOrders() {
        return storage.getAllOrders();
    }

    @GET
    @Path("{username}")
    @Produces({"application/xml", "application/json"})
    public ArrayList<Order> getOrders(@PathParam("username") String username) {
        return storage.getOrders(username);
    }

    @POST
    @Path("{inStore}")
    @Consumes({"application/xml", "application/json"})
    public Response placeOrder(Order order, @PathParam("inStore") boolean inStore) {
        StatusType status = storage.addOrder(order, inStore);
        if (status == StatusType.NONE) {
            return Response.noContent().build();
        } else {
            return Response.ok().entity(status.toString()).build();
        }
    }
    
    @PUT
    @Path("dispatchPending")
    @Consumes("application/xml")
    public Response dispatchOrder(Order order) {
        boolean success = storage.dispatchOrder(order.getId());
        return Response.ok(Boolean.toString(success)).build();
    }
    
    @GET
    @Path("requests")
    @Produces("application/xml")
    public ArrayList<Request> getAllRequests() {
        return storage.getAllRequests();
    }

    @POST
    @Path("deliverRequest")
    @Consumes("application/xml")
    public Response deliverRequest(Request request) {
        String msg = request.getTitle() + " request received";
        storage.addReceivedRequest(request);
        return Response.ok().entity(msg).build();
    }
    
    @POST
    @Path("acceptRequest")
    @Consumes("application/xml")
    public Response acceptRequest(Request request) {
        String msg = request.getTitle() + " request accepted";
        storage.acceptRequestDeliver(request.getId());
        return Response.ok().entity(msg).build();
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
}
