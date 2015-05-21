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

/**
 *
 * @author ASUS
 */
public class FXMLUpdateController implements Initializable {

    @FXML
    private Label bookTitleLabel;
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        System.out.println("Update button");
    }
    
    public void updateLabel(String bookTitle) {
        bookTitleLabel.setText(bookTitle);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
