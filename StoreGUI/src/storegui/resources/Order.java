/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui.resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private String clientEmail;
    private String status;

    public Order() {
        id = -1;
        title = null;
        quantity = -1;
        clientName = null;
        clientEmail = null;
        status = null;
    }

    public Order(String title, int quantity, String clientName,
            String clientEmail, String orderStatus) {
        this.id = ++counter;
        this.title = title;
        this.quantity = quantity;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.status = orderStatus;
    }

    public Order(Order order) {
        this.id = ++counter;
        this.title = order.getTitle();
        this.quantity = order.getQuantity();
        this.clientName = order.getClientName();
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
}
