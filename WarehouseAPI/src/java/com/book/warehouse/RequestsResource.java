/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.warehouse;

import com.book.warehouse.resources.Request;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author diogo
 */
@Stateless
@Path("requests")
public class RequestsResource {

    @Context
    private UriInfo context;
    @PersistenceContext(unitName = "WarehouseAPIPU")
    private EntityManager em;

    public RequestsResource() {
    }

    @GET
    @Produces("application/xml")
    public ArrayList<Request> getRequests() {
        return new ArrayList<>(em.createNamedQuery("Request.findAll").getResultList());
    }
    
    @DELETE
    @Path("{id}")
    public Response removeRequest(@PathParam("id") int id) {
        Request request = (Request) em.createNamedQuery("Request.findById")
                .setParameter("id", id).getResultList().get(0);
        em.remove(request);
        
        return Response.ok().entity("Request " + id + " removed.").build();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
