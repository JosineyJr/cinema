package com.cinema.main.views.users;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListUsersView {

  @FXML
  private TableColumn<?, ?> CPF;

  @FXML
  private TableColumn<?, ?> action;

  @FXML
  private TableColumn<?, ?> name;

  @FXML
  private TableColumn<?, ?> type;

  @FXML
  private TableView<?> users;

  @FXML
  public void initialize() {

  }
}
