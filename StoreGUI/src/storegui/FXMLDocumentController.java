/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.ws.rs.core.GenericType;
import storegui.resources.Book;
import storegui.resources.BooksResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    BooksResource_JerseyClient client;
    ArrayList<Book> availableBooks;
    GenericType<ArrayList<Book>> list = new GenericType<ArrayList<Book>>(){};
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
        availableBooks = client.getAllBooksInfo();
        System.err.println(availableBooks.get(0).getTitle());
    }
}
