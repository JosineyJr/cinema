package com.cinema.main.views.products;

import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import com.cinema.main.views.helpers.CurrencyField;

public class CreateProductView implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private CurrencyField price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ensure the CurrencyField is properly initialized with the desired locale
        if (price != null) {
            price.setCurrencyFormat(Locale.US);
        }
    }

    @FXML
    void registerProduct(ActionEvent event) {
        // Extract the values from the fields
        String productName = name.getText();
        Double productPrice = price.getAmount();

        // Handle product registration logic here
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        
        // You can add more logic here, like saving the product to a database or updating the UI
    }
}
