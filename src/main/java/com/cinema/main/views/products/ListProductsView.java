package com.cinema.main.views.products;

import java.util.List;

import com.cinema.application.dtos.products.DeleteProductDTO;
import com.cinema.application.dtos.products.ProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.DeleteProductFactory;
import com.cinema.main.factories.products.ListProductsFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
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

/**
 * The ListProductsView class represents a view for listing products in a cinema
 * application.
 * It displays a table with product information, such as name, price, and
 * quantity.
 * Users can perform actions like deleting and editing products.
 */
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

  /**
   * Initializes the ListProductsView.
   * This method is automatically called after the FXML file has been loaded.
   * It retrieves the product information and populates the products table.
   * It also sets up the cell factory for the action column, which contains delete
   * and edit buttons.
   */
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

    action.setCellFactory(new ActionCellFactory<>(this::editProduct, this::deleteProduct));
    action.setStyle("-fx-alignment: CENTER;");
  }

  /**
   * Event handler for creating a new product.
   *
   * @param event The mouse event that triggered the method.
   * @throws Exception If an error occurs while creating the product.
   */
  @FXML
  void createProduct(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/createProduct.fxml");
  }

  /**
   * Deletes the specified product.
   *
   * @param product The product to be deleted.
   */
  private void deleteProduct(ProductDTO product) {
    showConfirmationDialog(product);
  }

  /**
   * Displays a confirmation dialog for deleting a product.
   *
   * @param product The product to be deleted.
   */
  private void showConfirmationDialog(ProductDTO product) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText("Deseja realmente excluir o produto " + product.getName() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        Response<?> responseDelete = DeleteProductFactory.make()
            .handle(new DeleteProductDTO(product.getID(), product.getInventoryID()));

        if (responseDelete.getStatusCode() == 204) {
          new AlertSuccess("Produto deletado com sucesso!");
          productsTable.getItems().remove(product);
        } else {
          new AlertError(responseDelete.getData().toString());
        }
      }
    });
  }

  /**
   * Edits the specified product.
   *
   * @param product The product to be edited.
   * @throws Exception If an error occurs while editing the product.
   */
  private void editProduct(ProductDTO product) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ProductModel.getInstance().setProduct(product);

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/editProduct.fxml");
  }
}
