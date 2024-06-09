package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.DeleteMovieDTO;
import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.movies.MovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.DeleteMovieFactory;
import com.cinema.main.factories.movies.ListMoviesFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListMoviesView {

  @FXML
  private TableColumn<MovieDTO, Void> action;

  @FXML
  private TableColumn<MovieDTO, String> director;

  @FXML
  private TableColumn<MovieDTO, String> duration;

  @FXML
  private TableColumn<MovieDTO, GenreDTO> genre;

  @FXML
  private TableColumn<MovieDTO, String> minimumAge;

  @FXML
  private TableView<MovieDTO> moviesTable;

  @FXML
  private TableColumn<MovieDTO, String> title;

  @FXML
  void initialize() {
    Response<?> response = ListMoviesFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<MovieDTO> movies = FXCollections.observableArrayList();

      for (Object movie : (List<?>) data) {
        if (movie instanceof MovieDTO) {
          movies.add((MovieDTO) movie);
        }
      }

      moviesTable.setItems(movies);
    }

    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    title.setStyle("-fx-alignment: CENTER;");

    director.setCellValueFactory(new PropertyValueFactory<>("director"));
    director.setStyle("-fx-alignment: CENTER;");

    duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    duration.setStyle("-fx-alignment: CENTER;");

    genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    genre.setStyle("-fx-alignment: CENTER;");

    minimumAge.setCellValueFactory(new PropertyValueFactory<>("minimumAge"));
    minimumAge.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteMovie));
  }

  private void deleteMovie(MovieDTO movie) {
    showConfirmationDialog(movie);
  }

  private void showConfirmationDialog(MovieDTO movie) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText(null);
    alert.setContentText("Você realmente deseja excluir o filme '" + movie.getTitle() + "'?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        DeleteMovieDTO deleteMovieDTO = new DeleteMovieDTO(movie.getID());

        Response<?> deleteResponse = DeleteMovieFactory.make().handle(deleteMovieDTO);

        if (deleteResponse.getStatusCode() == 204) {
          new AlertSuccess("Filme com sucesso!");
          moviesTable.getItems().remove(movie);
        } else {
          new AlertError(deleteResponse.getData().toString());
        }
      }
    });
  }

  @FXML
  void createMovie(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovie.fxml");
  }
}
