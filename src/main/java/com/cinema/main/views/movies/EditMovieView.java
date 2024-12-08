package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.movies.MovieDTO;
import com.cinema.application.dtos.movies.UpdateMovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.ListGenresFactory;
import com.cinema.main.factories.movies.UpdateMovieFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The EditMovieView class represents the view for editing movie details.
 * It provides fields for editing the movie's title, director, duration, minimum
 * age, synopsis, and genre.
 * The view is responsible for populating the genre dropdown with available
 * genres,
 * setting the initial values for the movie's details, and handling the action
 * event when the "Edit Movie" button is clicked.
 */
public class EditMovieView {
  @FXML
  private TextField director;

  @FXML
  private TextField duration;

  @FXML
  private ChoiceBox<Item> genre;

  @FXML
  private TextField minimumAge;

  @FXML
  private TextField title;

  @FXML
  private TextArea synopsis;

  private MovieDTO movie;

  /**
   * Initializes the EditMovieView.
   * This method is automatically called when the view is loaded.
   * It populates the genre dropdown with available genres,
   * and sets the initial values for the movie's title, director, duration,
   * minimum age, and synopsis.
   * It also selects the genre that matches the movie's genre.
   */
  @FXML
  void initialize() {
    Response<?> response = ListGenresFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      for (Object genre : (List<?>) data) {
        if (genre instanceof GenreDTO) {
          this.genre.getItems().add(new Item(((GenreDTO) genre).getID(), ((GenreDTO) genre).getName()));
        }
      }
    }

    movie = MovieModel.getInstance().getMovie();

    title.setText(movie.getTitle());
    director.setText(movie.getDirector());
    duration.setText(String.valueOf(movie.getDuration()));
    minimumAge.setText(String.valueOf(movie.getMinimumAge()));
    synopsis.setText(movie.getSynopsis());

    Item genreItem = this.genre.getItems().stream().filter(item -> item.getID() == movie.getGenreID())
        .findFirst().orElse(null);
    
    System.out.println(genreItem);

    this.genre.setValue(genreItem);
  }

  /**
   * Handles the action event when the "Edit Movie" button is clicked.
   * Updates the movie details based on the user input and displays a success
   * message if the movie is edited successfully.
   * Otherwise, displays an error message with the response data.
   *
   * @param event the action event triggered by clicking the "Edit Movie" button
   * @throws Exception if an exception occurs during the movie update process
   */
  @FXML
  void editMovie(ActionEvent event) throws Exception {
    if (this.genre.getSelectionModel() == null || this.genre.getSelectionModel().getSelectedItem() == null) {
      new AlertError("Gênero é obrigatório");

      return;
    }

    String genreID = this.genre.getSelectionModel().getSelectedItem().getID().toString();

    UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO(movie.getID().toString(), title.getText(), director.getText(),
        synopsis.getText(), genreID, Integer.parseInt(duration.getText()), Integer.parseInt(minimumAge.getText()));

    Response<?> response = UpdateMovieFactory.make().handle(updateMovieDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Filme editado com sucesso");

      Stage primaryStage = StageManager.getPrimaryStage();

      ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listMovies.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
