package com.cinema.main.views.users;

import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Session;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

  @FXML
  void signOut() throws Exception {
    Session.signOut();
    Stage primaryStage = StageManager.getPrimaryStage();
    
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/auth/login.fxml");
  }
}
