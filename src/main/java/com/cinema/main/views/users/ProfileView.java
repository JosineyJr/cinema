package com.cinema.main.views.users;

import com.cinema.main.views.helpers.Session;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProfileView {
  @FXML
  private TextField cpf;

  @FXML
  private TextField name;

  @FXML
  void initialize() {
    cpf.setText(Session.getCPF());
    name.setText(Session.getName());
  }
}
