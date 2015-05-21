/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.warehouse;

import com.book.warehouse.resources.Request;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author diogo
 */
@JMSDestinationDefinition(name = "jms/WarehousePool", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "WarehousePool")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/WarehousePool"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class WarehouseMessageBean implements MessageListener {
    @PersistenceContext(unitName = "WarehouseAPIPU")
    private EntityManager em;

    public WarehouseMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                Request request = new Request(text);
                em.persist(request);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
