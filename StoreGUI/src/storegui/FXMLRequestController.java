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
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import storegui.resources.OrdersResource_JerseyClient;
import storegui.resources.Request;

/**
 *
 * @author ASUS
 */
public class FXMLRequestController implements Initializable {
    
    OrdersResource_JerseyClient ordersClient;
    ArrayList<Request> pendingRequests;
    Request currentRequestSelected;
    
    @FXML
    Label errorLabel, quantityLabel;
    
    @FXML
    ComboBox pendingRequestsComboBox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (pendingRequestsComboBox.getValue() != null && !pendingRequestsComboBox.getValue().toString().trim().isEmpty()) {
           int status = ordersClient.acceptRequest(currentRequestSelected).getStatus();
           System.out.println(status);
           if (status == 200) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
           } else {
                errorLabel.setText("Error accepting request");   
           }
        } else {
            errorLabel.setText("You have to choose a request!");
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ordersClient = new OrdersResource_JerseyClient();
        pendingRequests = ordersClient.getAllRequests();
        for (int i = 0, l = pendingRequests.size(); i < l; i++) {
            pendingRequestsComboBox.getItems().add(pendingRequests.get(i).getTitle());
        }
        
        pendingRequestsComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
               for (int i = 0, l = pendingRequests.size(); i < l; i++) {
                   if (pendingRequests.get(i).getTitle().equals(pendingRequestsComboBox.getValue())) {
                       currentRequestSelected = pendingRequests.get(i);
                       quantityLabel.setText("" + pendingRequests.get(i).getQuantity());
                   }
               }
            }    
        });
    }
}
