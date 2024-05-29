package com.cinema.main.views.users;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.ListGenresFactory;
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
import javafx.stage.Stage;

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
    Response<?> response = ListGenresFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      this.moviePreferences.setSpacing(5);

      for (Object genre : (List<?>) data) {
        if (genre instanceof GenreDTO) {
          GenreDTO genreDTO = (GenreDTO) genre;

          CheckBox chk = new CheckBox(genreDTO.getName());
          chk.setId(genreDTO.getID().toString());

          moviePreferences.getChildren().addAll(chk);
        }
      }
    }
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

    Response<?> response = CreateClientFactory.make().handle(createClientDTO);

    if (response.getStatusCode() == 200 || response.getStatusCode() == 204) {
      new AlertSuccess("Cliente criado com sucesso!");

      Stage stage = (Stage) createClientButton.getScene().getWindow();

      ChangeWindow.changeScene(stage, "/com/cinema/main/views/users/clientMoviesMenu.fxml");

    } else {
      new AlertError(response.getData().toString());
    }
  }

  @FXML
  void backLogin(MouseEvent event) throws Exception {
    Stage stage = (Stage) createClientButton.getScene().getWindow();

    ChangeWindow.changeScene(stage, "/com/cinema/main/views/users/login.fxml");
  }
}
