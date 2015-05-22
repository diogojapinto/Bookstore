/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import storegui.resources.Book;
import storegui.resources.Order;
import storegui.resources.OrdersResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLDispatchController implements Initializable {
    
    OrdersResource_JerseyClient ordersClient;
    ArrayList<Order> pendingOrders;
    Order currentOrderSelected;
    
    @FXML
    Label errorLabel;
    
    @FXML
    ComboBox pendingOrdersComboBox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (pendingOrdersComboBox.getValue() != null && !pendingOrdersComboBox.getValue().toString().trim().isEmpty()) {
            ordersClient.dispatchOrder(currentOrderSelected);
        } else {
            errorLabel.setText("You have to choose an order!");
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ordersClient = new OrdersResource_JerseyClient();
        pendingOrders = ordersClient.getAllOrders();
        for (int i = 0, l = pendingOrders.size(); i < l; i++) {
            if (pendingOrders.get(i).getStatus().equals("TO_BE_DISPATCHED")) {
                pendingOrdersComboBox.getItems().add(pendingOrders.get(i).getTitle());
            }
        }
        
        pendingOrdersComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
               for (int i = 0, l = pendingOrders.size(); i < l; i++) {
                   if (pendingOrders.get(i).getTitle().equals(pendingOrdersComboBox.getValue())) {
                       currentOrderSelected = pendingOrders.get(i);
                   }
               }
            }    
        });
    }
}
