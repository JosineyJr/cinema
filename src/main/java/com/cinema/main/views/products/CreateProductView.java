package com.cinema.main.views.products;

import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.cinema.application.dtos.products.CreateProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.CreateProductFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.CurrencyField;

/**
 * This class represents the view for creating a product.
 * It implements the Initializable interface to initialize the view.
 */
public class CreateProductView implements Initializable {

  @FXML
  private TextField name;

  @FXML
  private CurrencyField price;

  @FXML
  private TextField quantity;

  /**
   * Initializes the CreateProductView.
   * 
   * @param location  The location used to resolve relative paths for the root
   *                  object, or null if the location is not known.
   * @param resources The resources used to localize the root object, or null if
   *                  the root object was not localized.
   */
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

  /**
   * Registers a new product based on the input provided by the user.
   * 
   * @param event the action event triggered by the user
   */
  @FXML
  void registerProduct(ActionEvent event) throws Exception{
    CreateProductDTO createProductDTO = new CreateProductDTO(name.getText(), price.getAmount(),
        Integer.parseInt(quantity.getText()));

    Response<?> response = CreateProductFactory.make().handle(createProductDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Produto criado com sucesso!");

      Stage primaryStage = StageManager.getPrimaryStage();

      ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listProducts.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
