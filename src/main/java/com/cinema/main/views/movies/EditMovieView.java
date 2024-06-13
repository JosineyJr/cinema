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

public class EditMovieView {
  @FXML
  private TextField director;

  @FXML
  private TextField duration;

  @FXML
  private ChoiceBox<Item> genre;

  @FXML
  private TextField minimunAge;

  @FXML
  private TextField title;

  @FXML
  private TextArea synopsis;

  private MovieDTO movie;

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
    minimunAge.setText(String.valueOf(movie.getMinimumAge()));
    synopsis.setText(movie.getSynopsis());

    Item genreItem = this.genre.getItems().stream().filter(item -> item.getID().toString() == movie.getGenre())
        .findFirst().orElse(null);
    this.genre.setValue(genreItem);
  }

  @FXML
  void editMovie(ActionEvent event) throws Exception {
    String genreID = this.genre.getSelectionModel().getSelectedItem().getID().toString();

    UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO(movie.getID().toString(), title.getText(), director.getText(),
        synopsis.getText(), genreID, Integer.parseInt(duration.getText()), Integer.parseInt(minimunAge.getText()));

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
