package com.cinema.main.views.sales;

import java.util.List;

import com.cinema.application.dtos.sales.SaleDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.sales.ListSalesFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListSalesView {
  @FXML
  private TableView<SaleDTO> salesTable;

  @FXML
  private TableColumn<SaleDTO, String> cpf;

  @FXML
  private TableColumn<SaleDTO, String> salesCounter;

  @FXML
  private TableColumn<SaleDTO, Double> total;

  @FXML
  private TableColumn<SaleDTO, String> date;

  @FXML
  private TableColumn<SaleDTO, Void> action;

  @FXML
  void initialize() {
    Response<?> response = ListSalesFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<SaleDTO> sales = FXCollections.observableArrayList();

      for (Object sale : (List<?>) data) {
        SaleDTO saleDTO = (SaleDTO) sale;

        sales.add(saleDTO);
      }
    }
  }
}
