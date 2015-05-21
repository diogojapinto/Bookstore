/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import storegui.resources.Order;
import storegui.resources.OrdersResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLOrderController implements Initializable {
    
    @FXML
    TextField nBooks, clientName, titleWanted;
    
    OrdersResource_JerseyClient ordersClient;
    
    @FXML
    private void handleOrderButton(ActionEvent event) {
        if (nBooks.getText() != null && !nBooks.getText().trim().isEmpty() && 
                clientName.getText() != null && !clientName.getText().trim().isEmpty() && 
                titleWanted.getText() != null && !titleWanted.getText().trim().isEmpty()) {
            Order order = new Order();
            order.setQuantity(Integer.parseInt(nBooks.getText()));
            order.setTitle(titleWanted.getText());
            order.setClientName(clientName.getText());
            ordersClient.placeOrder(order, "false");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ordersClient = new OrdersResource_JerseyClient();
    }       
}
