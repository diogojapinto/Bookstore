/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehousegui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import warehousegui.resources.OrdersResource_JerseyClient;
import warehousegui.resources.Request;
import warehousegui.resources.RequestsResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    RequestsResource_JerseyClient requestsClient;
    OrdersResource_JerseyClient ordersClient;
    ArrayList<Request> availableRequests;
    
    @FXML
    ComboBox availableRequestsComboBox;
    
    @FXML
    TextField requestAmountDispatched;
    
    @FXML
    Label requestAmount, warningLabel;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        if (availableRequestsComboBox.getValue() != null && !availableRequestsComboBox.getValue().toString().trim().isEmpty() && requestAmountDispatched.getText() != null && !requestAmountDispatched.getText().trim().isEmpty()) {
            if (Integer.parseInt(requestAmountDispatched.getText()) > 0 && Integer.parseInt(requestAmountDispatched.getText()) <= Integer.parseInt(requestAmount.getText())) {
                Request request = new Request(availableRequestsComboBox.getValue().toString(),Integer.parseInt(requestAmountDispatched.getText()));
                System.out.println("Delivering request...");
                ordersClient.deliverRequest(request);
                System.out.println("Request delivered");
            } else {
                warningLabel.setText("The amount to be dispatched has to higher than 0 and lower than the original amount");
            }
        } else {
            warningLabel.setText("You have to fill in all the fields!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        requestsClient = new RequestsResource_JerseyClient();
        ordersClient = new OrdersResource_JerseyClient();
        availableRequests = requestsClient.getRequests();
        for (int i = 0, l = availableRequests.size(); i < l; i++) {
            availableRequestsComboBox.getItems().add(availableRequests.get(i).getTitle());
        }
        
        availableRequestsComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                for (int i = 0, l = availableRequests.size(); i < l; i++) {
                    if (availableRequestsComboBox.getValue().toString().equals(availableRequests.get(i).getTitle())) {
                        requestAmount.setText(""+availableRequests.get(i).getQuantity());
                        break;
                    }
                }
            }    
        });
    }    
    
}
