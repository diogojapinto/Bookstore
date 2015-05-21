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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    Label requestAmount;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        if (availableRequestsComboBox.getValue() != null && availableRequestsComboBox.getValue().toString().trim().isEmpty()) {
            Request request = new Request(availableRequestsComboBox.getValue().toString(),Integer.parseInt(requestAmount.getText()));
            ordersClient.deliverRequest(request);
        }
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUpdate.fxml"));

            Parent root = (Parent) loader.load();
            
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
            
            FXMLUpdateController controller = loader.<FXMLUpdateController>getController();
                     
            controller.updateLabel(availableRequestsComboBox.getValue().toString());
           
            stage.setScene(scene);
            
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
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
                        requestAmount.setText(""+availableRequests.get(i).getAmount());
                        break;
                    }
                }
            }    
        });
    }    
    
}
