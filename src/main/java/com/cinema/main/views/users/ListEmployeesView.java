package com.cinema.main.views.users;

import java.util.List;

import com.cinema.application.dtos.users.EmployeeDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.users.ListEmployeesFactory;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.CellValueFactoryUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListEmployeesView {
  @FXML
  private TableColumn<EmployeeDTO, String> CPF;

  @FXML
  private TableColumn<EmployeeDTO, Void> action;

  @FXML
  private TableColumn<EmployeeDTO, String> name;

  @FXML
  private TableView<EmployeeDTO> employeesTableView;

  @FXML
  public void initialize() {
    Response<?> response = ListEmployeesFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<EmployeeDTO> employees = FXCollections.observableArrayList();

      for (Object employee : (List<?>) data) {
        EmployeeDTO employeeDTO = (EmployeeDTO) employee;

        employees.add(employeeDTO);
      }

      this.employeesTableView.setItems(employees);
    }

    this.name.setCellValueFactory(CellValueFactoryUtil
        .createCellValueFactory(employee -> employee.getFirstName() + " " + employee.getLastName()));
    this.name.setStyle("-fx-alignment: CENTER;");

    this.CPF.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(EmployeeDTO::getCPF));
    this.CPF.setStyle("-fx-alignment: CENTER;");

    this.action.setCellFactory(new ActionCellFactory<>(this::editEmployee, this::deleteEmployee));
  }

  private void editEmployee(EmployeeDTO employee) {
    System.out.println("Edit employee: " + employee);
  }

  private void deleteEmployee(EmployeeDTO employee) {
    System.out.println("Delete employee: " + employee);
  }
}
