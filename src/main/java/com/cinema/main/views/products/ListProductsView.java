package com.cinema.main.views.products;

import com.cinema.application.dtos.products.InventoryDTO;
import com.cinema.application.dtos.products.ProductDTO;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class ListProductsView {
  @FXML
  private TableColumn<ProductDTO, Void> action;

  @FXML
  private TableColumn<ProductDTO, String> name;

  @FXML
  private TableColumn<ProductDTO, Double> price;

  @FXML
  private TableColumn<InventoryDTO, Integer> quantity;
}
