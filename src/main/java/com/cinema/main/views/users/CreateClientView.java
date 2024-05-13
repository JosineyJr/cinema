package com.cinema.main.views.users;

import java.util.ArrayList;

import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.adapters.JavaFxAdapter;
import com.cinema.main.factories.users.CreateClientFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CreateClientView {
  @FXML
  private TextField CPF;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private VBox moviePreferences;

  @FXML
  private PasswordField password;

  @FXML
  private PasswordField passwordConfirmation;

  @FXML
  private Button createClientButton;

  @FXML
  void initialize() {
    this.moviePreferences.setSpacing(5);
    CheckBox chkAction = new CheckBox("Ação");
    chkAction.setId("action");

    CheckBox chkTerror = new CheckBox("Terror");
    chkTerror.setId("terror");

    this.moviePreferences.getChildren().addAll(chkAction, chkTerror);
  }

  @FXML
  void createClient(ActionEvent event) throws Exception {
    ArrayList<String> genres = new ArrayList<>();

    this.moviePreferences.getChildren().forEach((node) -> {
      CheckBox chk = (CheckBox) node;
      if (chk.isSelected()) {
        genres.add(chk.getId());
      }
    });

    CreateClientDTO createClientDTO = new CreateClientDTO(
        firstName.getText(),
        lastName.getText(),
        CPF.getText(),
        password.getText(),
        passwordConfirmation.getText(),
        genres);

    @SuppressWarnings("rawtypes")
    Response response = JavaFxAdapter.adaptResolver(CreateClientFactory.make(), createClientDTO);

    if (response.getStatusCode() == 200 || response.getStatusCode() == 204) {
      new AlertSuccess("Cliente criado com sucesso!");

      new ChangeWindow("/com/cinema/main/views/users/clientMoviesMenu.fxml", event);

    } else {
      new AlertError(response.getData().toString());
    }
  }

  @FXML
  void backLogin(MouseEvent event) throws Exception {
    new ChangeWindow("/com/cinema/main/views/auth/login.fxml", event);
  }
}
