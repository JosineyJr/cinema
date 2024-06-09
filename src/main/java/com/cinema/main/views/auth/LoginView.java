package com.cinema.main.views.auth;

import com.cinema.application.dtos.auth.LoginDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.auth.LoginFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The LoginView class represents the graphical user interface for the login functionality.
 * It contains methods and fields related to the login view, such as text fields, labels, buttons, and event handlers.
 */
public class LoginView {

  @FXML
  private TextField CPF;

  @FXML
  private Label createClientLabel;

  @FXML
  private CheckBox isEmployee;

  @FXML
  private Button loginButton;

  @FXML
  private Label noRegistrationLabel;

  @FXML
  private TextField password;

  /**
   * Handles the event when the user clicks on the "Create Client" button.
   * This method is responsible for changing the current window to the "createClient.fxml" view.
   *
   * @param event The MouseEvent representing the user's click event.
   * @throws Exception if an error occurs while changing the window.
   */
  @FXML
  void createClient(MouseEvent event) throws Exception {
    Stage stage = (Stage) loginButton.getScene().getWindow();

    ChangeWindow.changeScene(stage, "/com/cinema/main/views/users/createClient.fxml");

  }

  /**
   * Handles the login action when the login button is clicked.
   * Retrieves the user input from the text fields and creates a LoginDTO object.
   * Calls the LoginFactory to handle the login request and gets the response.
   * If the response status code is 200, sets the user's CPF and role in the session.
   * Changes the scene to the client movies menu view.
   * If the response status code is not 200, displays an error alert with the response data.
   *
   * @param event The action event triggered by clicking the login button.
   * @throws Exception if an error occurs during the login process.
   */
  @FXML
  void login(ActionEvent event) throws Exception {
    LoginDTO loginDTO = new LoginDTO(CPF.getText(), password.getText(), isEmployee.isSelected());

    Response<?> response = LoginFactory.make().handle(loginDTO);

    if (response.getStatusCode() == 200) {
      Session.setCPF(loginDTO.getCPF());
      Session.setRole((String) response.getData());

      Stage stage = (Stage) loginButton.getScene().getWindow();

      ChangeWindow.changeScene(stage, "/com/cinema/main/views/products/listTicketsInfos.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }

  /**
   * Handles the event when the user chooses not to register.
   * This method changes the scene to the client movies menu view.
   *
   * @param event The mouse event that triggered the method.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void noRegistration(MouseEvent event) throws Exception {
    Stage stage = (Stage) loginButton.getScene().getWindow();

    ChangeWindow.changeScene(stage, "/com/cinema/main/views/users/clientMoviesMenu.fxml");
  }
}
