package com.cinema.main.views.auth;

import com.cinema.application.dtos.auth.LoginDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.adapters.JavaFxAdapter;
import com.cinema.main.factories.auth.LoginFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginView {

  @FXML
  private TextField CPF;

  @FXML
  private Label createClientLabel;

  @FXML
  private Button loginButton;

  @FXML
  private Label noRegistrationLabel;

  @FXML
  private TextField password;

  @FXML
  void createClient(MouseEvent event) throws Exception {
    new ChangeWindow("/com/cinema/main/views/users/createClient.fxml", event);
  }

  @FXML
  void login(ActionEvent event) throws Exception {
    LoginDTO loginDTO = new LoginDTO(CPF.getText(), password.getText());

    @SuppressWarnings("rawtypes")
    Response response = JavaFxAdapter.adaptResolver(LoginFactory.makeLogin(), loginDTO);

    if (response.getStatusCode() == 200) {
      Session.setCPF(loginDTO.getCPF());
      Session.setRole((String) response.getData());

      new ChangeWindow("/com/cinema/main/views/users/clientMoviesMenu.fxml", event);
    } else {
      new AlertError(response.getData().toString());
    }
  }

  @FXML
  void noRegistration(MouseEvent event) throws Exception {
    new ChangeWindow("/com/cinema/main/views/users/clientMoviesMenu.fxml", event);
  }
}
