package com.cinema.main.views.users;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.main.adapters.JavaFxAdapter;
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
    Object response = JavaFxAdapter.adaptResolver(ListGenresFactory.make(), null);

    if (response.getStatusCode() == 200) {
      if (response.getData() instanceof List) {
        List<GenreDTO> genres = (List) response.getData();

        genres.forEach((genre) -> {
          CheckBox chk = new CheckBox(genre.getName());
          chk.setId(genre.getID());
          this.moviePreferences.getChildren().add(chk);
        });
      } else {
        new AlertError("Erro ao buscar os gêneros");
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

    Response<?> response = JavaFxAdapter.adaptResolver(CreateClientFactory.make(), createClientDTO);

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
