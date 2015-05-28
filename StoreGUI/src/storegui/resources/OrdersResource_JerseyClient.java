/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui.resources;

import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author diogo
 */
public class OrdersResource_JerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/StoreAPI/webresources";

    public OrdersResource_JerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("orders");
    }

    public Response deliverRequest(Object requestEntity) throws ClientErrorException {
        return webTarget.path("deliverRequest").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response placeOrder(Object requestEntity, String inStore) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{inStore})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }
    
    public Response dispatchOrder(Object requestEntity) {
        return webTarget.path("dispatchPending").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }
    
    public <T> T getOrders(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public ArrayList<Order> getAllOrders() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<ArrayList<Order>>(){});
    }
    
    public ArrayList<Request> getAllRequests() throws ClientErrorException{
        WebTarget resource = webTarget;
        return resource.path("requests").request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<ArrayList<Request>>(){});
    }
    
    public Response acceptRequest(Object requestEntity) throws ClientErrorException{
        return webTarget.path("acceptRequest").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public void close() {
        client.close();
    }
}
