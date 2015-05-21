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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storegui.resources.Book;
import storegui.resources.BooksResource_JerseyClient;

/**
 *
 * @author ASUS
 */
public class FXMLUpdateController implements Initializable {
    
    BooksResource_JerseyClient client;
    
    @FXML
    private Label titleLabel, currentStockLabel, warningLabel;
    
    @FXML
    private TextField nBooks;
    
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        if (nBooks.getText() != null && !nBooks.getText().trim().isEmpty()) {
            Book book = new Book(titleLabel.getText());
            book.setStock(Integer.parseInt(nBooks.getText()));
            client.updateStoreStock(book);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } else {
            warningLabel.setText("The quantity field is empty!");
        }
    }
    
    public void updateLabel(String text) {
        titleLabel.setText(text);
        Book book = client.getBookInfo(titleLabel.getText());
        currentStockLabel.setText(String.valueOf(book.getStock()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = new BooksResource_JerseyClient();
    }    
}
