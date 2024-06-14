package com.cinema.main.views.sales;

import java.util.List;

import com.cinema.application.dtos.sales.SaleDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.sales.ListSalesFactory;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.CellValueFactoryUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

      salesTable.setItems(sales);
    }

    this.cpf.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(SaleDTO::getCPF));
    this.cpf.setStyle("-fx-alignment: CENTER;");

    this.salesCounter.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(SaleDTO::getSalesCounter));
    this.salesCounter.setStyle("-fx-alignment: CENTER;");

    this.total.setCellValueFactory(new PropertyValueFactory<>("total"));
    this.total.setStyle("-fx-alignment: CENTER;");

    this.date.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(SaleDTO::getDate));
    this.date.setStyle("-fx-alignment: CENTER;");

    this.action.setCellFactory(new ActionCellFactory<>(this::viewDetails, this::cancelSale, "Detalhes", "Cancelar"));
  }

  private void viewDetails(SaleDTO sale) {
    System.out.println("View details of sale: " + sale);
  }

  private void cancelSale(SaleDTO sale) {
    System.out.println("Cancel sale: " + sale);
  }
}
