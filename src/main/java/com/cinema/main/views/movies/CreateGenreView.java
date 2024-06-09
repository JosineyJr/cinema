package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.CreateGenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.CreateGenreFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This class represents the view for creating a genre.
 */
public class CreateGenreView {

  @FXML
  private TextField genreNameField;

  @FXML
  private Button registerButton;

  /**
   * Event handler for the register button.
   * Retrieves the genre name from the text field, creates a CreateGenreDTO object,
   * and calls the handle method of CreateGenreFactory to create the genre.
   * Displays a success or error alert based on the response status code.
   *
   * @param event The action event triggered by the register button.
   */
  @SuppressWarnings("rawtypes")
  @FXML
  void register(ActionEvent event) {
    String genreName = genreNameField.getText();

    CreateGenreDTO createGenreDTO = new CreateGenreDTO(genreName);

    Response response = CreateGenreFactory.make().handle(createGenreDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Gênero criado com sucesso!");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
