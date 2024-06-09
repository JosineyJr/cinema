package com.cinema.main.views.products;

import java.util.List;

import com.cinema.application.dtos.products.DeleteProductInfosDTO;
import com.cinema.application.dtos.products.ProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.DeleteProductFactory;
import com.cinema.main.factories.products.ListProductsInfosFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
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
  private TableColumn<ProductInfosDTO, Void> action;

  @FXML
  private TableColumn<ProductInfosDTO, String> name;

  @FXML
  private TableColumn<ProductInfosDTO, Double> price;

  @FXML
  private TableColumn<ProductInfosDTO, Integer> quantity;

  @FXML
  private TableView<ProductInfosDTO> productsTable;

  @FXML
  void initialize() {
    Response<?> response = ListProductsInfosFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<ProductInfosDTO> products = FXCollections.observableArrayList();

      for (Object product : (List<?>) data) {
        if (product instanceof ProductInfosDTO) {
          products.add((ProductInfosDTO) product);
        }
      }

      productsTable.setItems(products);
    }

    name.setCellValueFactory(new PropertyValueFactory<ProductInfosDTO, String>("name"));
    name.setStyle("-fx-alignment: CENTER;");

    price.setCellValueFactory(new PropertyValueFactory<ProductInfosDTO, Double>("price"));
    price.setStyle("-fx-alignment: CENTER;");

    quantity.setCellValueFactory(new PropertyValueFactory<ProductInfosDTO, Integer>("quantity"));
    quantity.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteProduct));

  }

  @FXML
  void createProduct(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/createProduct.fxml");
  }

  private void deleteProduct(ProductInfosDTO product) {
    showConfirmationDialog(product);
  }

  private void showConfirmationDialog(ProductInfosDTO product) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText("Deseja realmente excluir o produto " + product.getName() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        productsTable.getItems().remove(product);

        Response<?> responseDelete = DeleteProductFactory.make()
            .handle(new DeleteProductInfosDTO(product.getID(), product.getInventoryID()));

        if (responseDelete.getStatusCode() == 204) {
          new AlertSuccess("Produto deletado com sucesso!");
        } else {
          new AlertError(responseDelete.getData().toString());
        }
      }
    });
  }
}
