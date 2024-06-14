package com.cinema.main.views.sales;

import com.cinema.application.dtos.sales.SaleDTO;
import com.cinema.application.dtos.sales.SaleItemDTO;
import com.cinema.main.views.helpers.CellValueFactoryUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewSalesDetailsView {
  @FXML
  private TableView<SaleItemDTO> salesItemTable;

  @FXML
  private TableColumn<SaleItemDTO, String> name;

  @FXML
  private TableColumn<SaleItemDTO, Double> price;

  @FXML
  private TextField date;

  @FXML
  private TextField cpf;

  @FXML
  private TextField salesCounter;

  @FXML
  private TextField total;

  @FXML
  void initialize() {
    SaleDTO sale = SaleModel.getInstance().getSale();

    this.date.setText(sale.getDate());
    this.cpf.setText(sale.getCPF());
    this.salesCounter.setText(sale.getSalesCounter());
    this.total.setText(String.valueOf(sale.getTotal()));

    ObservableList<SaleItemDTO> salesItems = FXCollections.observableArrayList();
    salesItems.addAll(sale.getItems());

    this.salesItemTable.setItems(salesItems);

    this.name.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(SaleItemDTO::getName));
    this.name.setStyle("-fx-alignment: CENTER;");

    this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
    this.price.setStyle("-fx-alignment: CENTER;");
  }
}
