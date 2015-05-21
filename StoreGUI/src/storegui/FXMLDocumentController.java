/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.book.store.*;
import java.net.URL;
import java.util.ResourceBundle;
import javax.ws.rs.core.GenericType;
import resources.BooksResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    BooksResource_JerseyClient client;
    ArrayList<IBook> availableBooks;
    GenericType<ArrayList<IBook>> list = new GenericType<ArrayList<IBook>>(){};
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
        client = new BooksResource_JerseyClient(); 
        availableBooks = client.getAllBooksInfo(new GenericType<ArrayList<IBook>>(){});
    }
}
