/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import storegui.resources.Book;
import storegui.resources.OrdersResource_JerseyClient;
import storegui.resources.Request;

/**
 *
 * @author ASUS
 */
public class FXMLUpdateController implements Initializable {
    
    OrdersResource_JerseyClient client;
    ArrayList<Request> requests;
    Request currentSelectedRequest;
    
    @FXML
    private Label stockAmount, warningLabel;
    
    @FXML
    private ComboBox requestsComboBox;
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        if (requestsComboBox.getValue() != null && !requestsComboBox.getValue().toString().trim().isEmpty()) {
            client.acceptRequest(currentSelectedRequest);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } else {
            warningLabel.setText("All values must be filled in!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = new OrdersResource_JerseyClient();
        requests = client.getAllRequests();
        for (int i = 0, l = requests.size(); i < l; i++) {
            requestsComboBox.getItems().add(requests.get(i).getTitle());
        }
        
        requestsComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                for (int i = 0, l = requests.size(); i < l; i++) {
                    if (requests.get(i).getTitle().equals(requestsComboBox.getValue().toString())) {
                        currentSelectedRequest = requests.get(i);
                    }
                }
            }    
        });
    }    
}
