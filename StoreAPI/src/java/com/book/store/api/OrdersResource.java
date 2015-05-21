/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.resources.Book;
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

    @POST
    @Path("deliverRequest")
    @Consumes("application/xml")
    public Response deliverRequest(Request request) {
        Book book = (Book) em.createNamedQuery("Book.findByTitle")
                .setParameter("title", request.getTitle()).getResultList().get(0);

        book.setStock(book.getStock() + request.getAmount());
        em.persist(book);

        String msg = request.getTitle() + " stock updated to " + book.getStock();
        return Response.ok().entity(msg).build();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
