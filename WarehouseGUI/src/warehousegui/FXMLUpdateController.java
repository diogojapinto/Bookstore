/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehousegui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import warehousegui.resources.RequestsResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLUpdateController implements Initializable {
    
    RequestsResource_JerseyClient requestsClient;
    
    @FXML
    private Label bookTitleLabel;
    
    @FXML
    private TextField requestAmount;
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        System.out.println("Update button");
        if (requestAmount.getText() != null && !requestAmount.getText().trim().isEmpty()) {
            //TODO update request
        }
    }
    
    public void updateLabel(String bookTitle) {
        bookTitleLabel.setText(bookTitle);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        requestsClient = new RequestsResource_JerseyClient();
    }    
    
}
