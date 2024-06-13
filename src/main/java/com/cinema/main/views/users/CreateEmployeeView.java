package com.cinema.main.views.users;

import com.cinema.application.dtos.users.CreateEmployeeDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.users.CreateEmployeeFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEmployeeView {
  @FXML
  private TextField CPF;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private PasswordField password;

  @FXML
  private PasswordField passwordConfirmation;

  @FXML
  void createEmployee(ActionEvent event) throws Exception {
    CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
        firstName.getText(),
        lastName.getText(),
        CPF.getText(),
        password.getText(),
        passwordConfirmation.getText());

    Response<?> response = CreateEmployeeFactory.make().handle(createEmployeeDTO);

    if (response.getStatusCode() == 200 || response.getStatusCode() == 204) {
      new AlertSuccess("Cliente criado com sucesso!");

      Stage primaryStage = StageManager.getPrimaryStage();

      ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/profile.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
