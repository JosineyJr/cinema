package com.cinema.main.views.products;

import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import com.cinema.application.dtos.products.CreateProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.CreateProductFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.CurrencyField;

public class CreateProductView implements Initializable {

  @FXML
  private TextField name;

  @FXML
  private CurrencyField price;

  @FXML
  private TextField quantity;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (price != null) {
      price.setCurrencyFormat(Locale.US);
    }

    quantity.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
          quantity.setText("0");
      }
  });
  }

  @FXML
  void registerProduct(ActionEvent event) {
    CreateProductDTO createProductDTO = new CreateProductDTO(name.getText(), price.getAmount(),
        Integer.parseInt(quantity.getText()));

    Response<?> response = CreateProductFactory.make().handle(createProductDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Produto criado com sucesso!");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
