package com.cinema.main.views.users;

import java.util.ArrayList;

import com.cinema.application.dtos.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.users.CreateClientFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateClientView {
  @FXML
  private TextField CPF;

  @FXML
  private Button createClientButton;

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
  void initialize() {
    this.moviePreferences.setSpacing(5);
    CheckBox chkAction = new CheckBox("Ação");
    chkAction.setId("action");

    CheckBox chkTerror = new CheckBox("Terror");
    chkTerror.setId("terror");

    this.moviePreferences.getChildren().addAll(chkAction, chkTerror);
  }

  @FXML
  void createClient(ActionEvent event) {

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

    Response response = CreateClientFactory.makCreateClientController().handle(createClientDTO);

    if (response.getStatusCode() == 200 || response.getStatusCode() == 204) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText("Client created successfully!");
      alert.showAndWait();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText(response.getData().toString());
      alert.showAndWait();
    }
  }
}
