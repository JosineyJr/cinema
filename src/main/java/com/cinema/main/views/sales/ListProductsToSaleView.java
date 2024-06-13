package com.cinema.main.views.sales;

import java.util.List;

import com.cinema.application.dtos.products.ProductDTO;
import com.cinema.application.dtos.sales.AddProductToCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.ListProductsFactory;
import com.cinema.main.factories.sales.AddProductToCartFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListProductsToSaleView {

  @FXML
  private TableColumn<ProductDTO, Void> action;

  @FXML
  private TableView<ProductDTO> availableProducts;

  @FXML
  private TableColumn<ProductDTO, String> name;

  @FXML
  private TableColumn<ProductDTO, String> price;

  @FXML
  public void initialize() {
    Response<?> response = ListProductsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<ProductDTO> products = FXCollections.observableArrayList();

      for (Object product : (List<?>) data) {
        if (product instanceof ProductDTO) {
          ProductDTO productDTO = (ProductDTO) product;

          products.add(productDTO);
        }
      }

      this.availableProducts.setItems(products);
    }

    this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.name.setStyle("-fx-alignment: CENTER;");

    this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
    this.price.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Add carrinho", this::addToCart));
  }

  private void addToCart(ProductDTO product) {
    AddProductToCartDTO addProductToCartDTO = new AddProductToCartDTO(product.getID().toString(), Session.getCPF());

    Response<?> response = AddProductToCartFactory.make().handle(addProductToCartDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Produto adicionado ao carrinho com sucesso!");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
