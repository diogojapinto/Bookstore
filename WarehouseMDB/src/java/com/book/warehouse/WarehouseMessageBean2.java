/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.warehouse;

import com.book.store.Request;
import java.util.ArrayList;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author diogo
 */
@JMSDestinationDefinition(name = "jms/WarehousePool", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "WarehousePool")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/WarehousePool"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class WarehouseMessageBean2 implements MessageListener {
    
    private ArrayList<Request> requests = new ArrayList<>();

    public WarehouseMessageBean2() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                Request request = new Request(text);
                requests.add(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
