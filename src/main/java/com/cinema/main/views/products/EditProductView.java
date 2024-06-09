package com.cinema.main.views.products;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.cinema.application.dtos.products.EditProductInfosDTO;
import com.cinema.application.dtos.products.ProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.UpdateProductFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.CurrencyField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class EditProductView implements Initializable {
  @FXML
  private TextField name;

  @FXML
  private CurrencyField price;

  @FXML
  private TextField quantity;

  private ProductInfosDTO product;

  public void initialize(URL location, ResourceBundle resources) {
    if (price != null) {
      price.setCurrencyFormat(Locale.US);
    }

    quantity.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        quantity.setText("0");
      }
    });

    product = ProductModel.getInstance().getProduct();

    name.setText(product.getName());
    price.setAmount(product.getPrice());
    quantity.setText(String.valueOf(product.getQuantity()));
  }

  @FXML
  void editProduct(ActionEvent event) {
    EditProductInfosDTO updateProductDTO = new EditProductInfosDTO(product.getID(), name.getText(), price.getAmount(),
        Integer.parseInt(quantity.getText()), product.getInventoryID());

    Response<?> response = UpdateProductFactory.make().handle(updateProductDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Produto editado com sucesso!");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
