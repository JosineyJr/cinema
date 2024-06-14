package com.cinema.main.views.movies;

import java.util.Iterator;
import java.util.List;

import com.cinema.application.dtos.movies.DeleteMovieDTO;
import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.movies.MovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.DeleteMovieFactory;
import com.cinema.main.factories.movies.ListMoviesFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.movies.comparators.MovieStringComparator;

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

/**
 * The ListMoviesView class represents the view for listing movies in the cinema
 * application.
 * It displays a table of movies with various details such as title, director,
 * duration, genre, and minimum age.
 * The view also provides functionality to delete a movie and create a new
 * movie.
 */
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

  /**
   * Initializes the ListMoviesView.
   * This method is automatically called after the FXML file has been loaded.
   * It retrieves a list of movies and populates the moviesTable with the data.
   * It also sets up the cell value factories and styles for the table columns.
   * Finally, it sets up the action cell factory for the "Excluir" button.
   */
  @FXML
  void initialize() {
    Response<?> response = ListMoviesFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<MovieDTO> movies = FXCollections.observableArrayList();

      Iterator<?> iterator = ((List<?>) data).iterator();

      while (iterator.hasNext()) {
        if (iterator.next() instanceof MovieDTO) {
          MovieDTO movie = (MovieDTO) iterator.next();
          movies.add(movie);
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

    action.setCellFactory(new ActionCellFactory<>(this::editMovie, this::deleteMovie, "Editar", "Deletar"));

    title.setComparator(new MovieStringComparator());
    duration.setComparator(new MovieStringComparator());
  }

  /**
   * Deletes a movie from the system.
   *
   * @param movie The movie to be deleted.
   */
  private void deleteMovie(MovieDTO movie) {
    showConfirmationDialog(movie);
  }

  /**
   * Displays a confirmation dialog for deleting a movie.
   * 
   * @param movie The MovieDTO object representing the movie to be deleted.
   */
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

  /**
   * Event handler for creating a new movie.
   * This method is called when the user clicks on a button to create a new movie.
   * It opens a new window for creating a movie.
   *
   * @param event The mouse event that triggered the method.
   * @throws Exception If an error occurs while creating the movie.
   */
  @FXML
  void createMovie(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovie.fxml");
  }

  void editMovie(MovieDTO movie) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    MovieModel.getInstance().setMovie(movie);

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/editMovie.fxml");
  }

}
