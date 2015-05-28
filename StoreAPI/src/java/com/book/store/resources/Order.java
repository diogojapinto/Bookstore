/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.store.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diogo
 */
@XmlRootElement
public class Order {

    private static int counter = 0;

    public enum StatusType {

        DELIVERED,
        DISPATCHED,
        TO_BE_DISPATCHED,
        WAITING_EXPEDITION,
        NONE
    };

    public static String getDeliveredStatus() {
        return "delivered at " + addDaysToNow(0);
    }

    public static String getDispatchedStatus(int days) {
        return "dispatched at " + addDaysToNow(days);
    }

    public static String getToBeDispatchedStatus(int days) {
        return "dispatch will occur at " + addDaysToNow(days);
    }

    public static String getWaitingExpeditionStatus() {
        return "waiting expedition";
    }

    public static StatusType getOrderStatusType(String status) {
        if (status.contains("delivered")) {
            return StatusType.DELIVERED;
        } else if (status.contains("dispatched")) {
            return StatusType.DISPATCHED;
        } else if (status.contains("waiting")) {
            return StatusType.WAITING_EXPEDITION;
        } else if (status.contains("dispatch will occur")) {
            return StatusType.TO_BE_DISPATCHED;
        } else {
            return StatusType.NONE;
        }
    }

    private static String addDaysToNow(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, days);
        return sdf.format(c.getTime());
    }

    private int id;
    private String title;
    private int quantity;
    private String clientName;
    private String clientAddress;
    private String clientEmail;
    private String status;

    public Order() {
        id = -1;
        title = null;
        quantity = -1;
        clientName = null;
        clientAddress = null;
        clientEmail = null;
        status = null;
    }

    public Order(String title, int quantity, String clientName, String clientAddress,
            String clientEmail, String orderStatus) {
        this.id = ++counter;
        this.title = title;
        this.quantity = quantity;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.status = orderStatus;
    }

    public Order(Order order) {
        this.id = ++counter;
        this.title = order.getTitle();
        this.quantity = order.getQuantity();
        this.clientName = order.getClientName();
        this.clientAddress = order.getClientAddress();
        this.clientEmail = order.getClientEmail();
        status = null;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    
    
    public void printReceipt() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh_mm_ss");
        String date = sdf.format(new Date());

        Path path = Paths.get(System.getProperty("user.home"), "receipts", date + ".txt");
        File logFile = path.toFile();
        logFile.getParentFile().mkdirs();

        try {
            PrintWriter writer = new PrintWriter(logFile);

            writer.println("Title:\t" + title);
            writer.println("Quantity:\t" + quantity);
            writer.println("Total Cost:\t" + this.getTotalCost());
            writer.println();
            writer.println("Client:\t" + clientName);
            writer.println("Address:\t" + clientAddress);
            writer.println("E-mail:\t" + clientEmail);
            writer.println("Status:\t" + status);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendReceiptToEmail() {
        String subject = "Order from " + clientName;
        StringBuilder body = new StringBuilder();

        body.append("Title:\t" + title + "\n");
        body.append("Quantity:\t" + quantity + "\n");
        body.append("Total Cost:\t" + getTotalCost() + "\n");
        body.append("\n");
        body.append("Client:\t" + clientName + "\n");
        body.append("Address:\t" + clientAddress + "\n");
        body.append("E-mail:\t" + clientEmail + "\n");
        body.append("Status:\t" + status + "\n");
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.fe.up.pt");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.ssl.enable", true);

        Session session = Session.getInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("user@bookstore.com"));
            InternetAddress[] address = {new InternetAddress(clientEmail)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body.toString());
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private float getTotalCost() {
        BooksResource_JerseyClient resource = new BooksResource_JerseyClient();
        
        Book book = resource.getBookInfo(Book.class, title);

        if (book == null) {
            return -1;
        } else {
            return (float) (quantity * book.getPrice());
        }
    }

    static class BooksResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/StoreAPI/webresources";

        public BooksResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("books");
        }

        public Response updateStoreStock(Object requestEntity) throws ClientErrorException {
            return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
        }

        public <T> T getBookInfo(Class<T> responseType, String title) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{title}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public <T> T getAllBooksInfo(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

}
