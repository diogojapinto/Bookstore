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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storegui.resources.Book;
import storegui.resources.BooksResource_JerseyClient;
import storegui.resources.Order;
import storegui.resources.OrdersResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    BooksResource_JerseyClient booksClient;
    OrdersResource_JerseyClient ordersClient;
    ArrayList<Book> availableBooks;
    
    @FXML
    Label totalPriceLabel, warningLabel, statusLabel;
    
    @FXML
    TextField nBooks, clientName, address, email;
    
    @FXML
    ComboBox availableTitles;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (nBooks.getText() != null && !nBooks.getText().trim().isEmpty() && 
                clientName.getText() != null && !clientName.getText().trim().isEmpty() && 
                availableTitles.getValue() != null && !availableTitles.getValue().toString().trim().isEmpty() &&
                address.getText() != null && !address.getText().trim().isEmpty() &&
                email.getText() != null && !email.getText().trim().isEmpty()) {
            Order order = new Order(availableTitles.getValue().toString(), Integer.parseInt(nBooks.getText()), clientName.getText(), address.getText(), email.getText(), null);
            String returnedValue = ordersClient.placeOrder(order, "true").readEntity(String.class);
            System.out.println(returnedValue);
            if (returnedValue.equals("DELIVERED")) {
                statusLabel.setText("Book delivered!");
            } else if (returnedValue.equals("WAITING_EXPEDITION")) {
                statusLabel.setText("Insufficient stock, asked the warehouse for more!");
            }
        } else {
            warningLabel.setText("Values not set!");
       }
    }
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUpdate.fxml"));

            Parent root = (Parent) loader.load();
        
            Scene scene = new Scene(root);
        
            Stage stage = new Stage();
        
            stage.setScene(scene);
        
            stage.show();
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleDispatchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDispatch.fxml"));

            Parent root = (Parent) loader.load();
        
            Scene scene = new Scene(root);
        
            Stage stage = new Stage();
        
            stage.setScene(scene);
        
            stage.show();
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        booksClient = new BooksResource_JerseyClient(); 
        ordersClient = new OrdersResource_JerseyClient();
        availableBooks = booksClient.getAllBooksInfo();
        for (int i = 0, l = availableBooks.size(); i < l; i++) {
            availableTitles.getItems().add(availableBooks.get(i).getTitle());
        }
        
        nBooks.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                try{
                    Book book = booksClient.getBookInfo(availableTitles.getValue().toString());
                    totalPriceLabel.setText(""+book.getPrice()*Integer.parseInt(nBooks.getText()));
                } catch (Exception e) {
                    
                }
            }
        });
        
        availableTitles.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if (nBooks.getText() != null && !nBooks.getText().trim().isEmpty()) {
                    Book book = booksClient.getBookInfo(availableTitles.getValue().toString());
                    totalPriceLabel.setText(""+book.getPrice()*Integer.parseInt(nBooks.getText()));
                }
            }    
        });
    }
}
