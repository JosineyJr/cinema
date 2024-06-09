package com.cinema.main.views.products;

import java.util.List;

import com.cinema.application.dtos.products.ProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.ListProductsFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListProductsView {
  @FXML
  private TableColumn<ProductDTO, Void> action;

  @FXML
  private TableColumn<ProductDTO, String> name;

  @FXML
  private TableColumn<ProductDTO, Double> price;

  @FXML
  private TableColumn<ProductDTO, Integer> quantity;

  @FXML
  private TableView<ProductDTO> productsTable;

  @FXML
  void initialize() {
    Response<?> response = ListProductsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<ProductDTO> products = FXCollections.observableArrayList();

      for (Object product : (List<?>) data) {
        if (product instanceof ProductDTO) {
          products.add((ProductDTO) product);
        }
      }

      productsTable.setItems(products);
    }

    name.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("name"));
    name.setStyle("-fx-alignment: CENTER;");

    price.setCellValueFactory(new PropertyValueFactory<ProductDTO, Double>("price"));
    price.setStyle("-fx-alignment: CENTER;");

    quantity.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("quantity"));
    quantity.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteProduct));

  }

  @FXML
  void createProduct(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/createProduct.fxml");
  }

  private void deleteProduct(ProductDTO product) {
  }

  private void showConfirmationDialog(ProductDTO product) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText("Deseja realmente excluir o produto " + product.getName() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        productsTable.getItems().remove(product);
      }
    });
  }
}
