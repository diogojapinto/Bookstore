/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storeclient;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storeclient.resources.*;
import com.book.store.IBook;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    BooksResource_JerseyClient resClient;
    ArrayList<IBook> availableBooks;
    String coiso;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void handleOrderButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLOrder.fxml"));
        
            Scene scene = new Scene(root);
        
            Stage stage = new Stage();
        
            stage.setScene(scene);
        
            stage.show();
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLUpdate.fxml"));
        
            Scene scene = new Scene(root);
        
            Stage stage = new Stage();
        
            stage.setScene(scene);
        
            stage.show();
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resClient = new BooksResource_JerseyClient();
        //coiso = resClient.getAllBooksInfo(String.class);
        //System.out.println(coiso);
    }
}
