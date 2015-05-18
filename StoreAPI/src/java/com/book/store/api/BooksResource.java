/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.api;

import com.book.store.resources.Book;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author diogo
 */
@Stateless
@Path("books")
public class BooksResource {

    @Context
    private UriInfo context;
    @PersistenceContext(unitName = "StoreAPIPU")
    private EntityManager em;

    public BooksResource() {
    }

    @GET
    @Path("/getInfo/{title}")
    @Produces("application/xml")
    public Book getBookInfo(@PathParam("username") String title) {
        return (Book) em.createNamedQuery("Book.findByTitle")
                .setParameter("title", title).getResultList().get(0);
    }

    @PUT
    @Consumes("application/xml")
    public Response updateStoreStock(Book book) {
        Book persistentBook = (Book) em.createNamedQuery("Book.findByTitle")
                .setParameter("title", book.getTitle()).getResultList().get(0);
        persistentBook.setStock(book.getStock());
        em.persist(persistentBook);
        return Response.ok().build();
    }
}
