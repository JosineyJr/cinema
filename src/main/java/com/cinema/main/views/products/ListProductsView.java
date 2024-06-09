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
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    action.setCellFactory(column -> {
      TableCell<ProductInfosDTO, Void> cell = new TableCell<>() {
        private final Button deleteButton = new Button("Excluir");
        private final Button editButton = new Button("Editar");

        {
          deleteButton.setOnAction(event -> deleteProduct(getTableView().getItems().get(getIndex())));
          editButton.setOnAction(event -> {
            try {
              editProduct(getTableView().getItems().get(getIndex()));
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setGraphic(null);
          } else {
            HBox buttons = new HBox(deleteButton, editButton);
            buttons.setSpacing(5);
            buttons.setStyle("-fx-alignment: CENTER;");
            setGraphic(buttons);
          }
        }
      };
      return cell;
    });
    action.setStyle("-fx-alignment: CENTER;");
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

  private void editProduct(ProductInfosDTO product) throws Exception{
    Stage primaryStage = StageManager.getPrimaryStage();

    ProductModel.getInstance().setProduct(product);

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/editProduct.fxml");
  }
}
