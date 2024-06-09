package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.DeleteMovieSessionDTO;
import com.cinema.application.dtos.movies.MovieSessionDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.DeleteMovieSessionFactory;
import com.cinema.main.factories.movies.ListMovieSessionsFactory;
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

/**
 * The ListMovieSessionsView class represents a view for listing movie sessions.
 * It displays a table of movie sessions with columns for movie, cinema hall, and start time.
 * Users can delete a movie session by clicking on the "Excluir" button in the action column.
 * Users can also create a new movie session by clicking on a button.
 */
public class ListMovieSessionsView {

  @FXML
  private TableColumn<MovieSessionDTO, Void> action;

  @FXML
  private TableColumn<MovieSessionDTO, String> cinemaHall;

  @FXML
  private TableColumn<MovieSessionDTO, String> movie;

  @FXML
  private TableView<MovieSessionDTO> movieSessionTable;

  @FXML
  private TableColumn<MovieSessionDTO, String> startTime;

  /**
   * Initializes the ListMovieSessionsView.
   * This method is automatically called after the FXML file has been loaded.
   * It retrieves the movie sessions data and populates the table view with the data.
   * It also sets up the cell value factories and styles for the table columns.
   */
  @FXML
  void initialize() {
    Response<?> response = ListMovieSessionsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<MovieSessionDTO> movieSessions = FXCollections.observableArrayList();

      for (Object movieSession : (List<?>) data) {
        if (movieSession instanceof MovieSessionDTO) {
          movieSessions.add((MovieSessionDTO) movieSession);
        }
      }

      movieSessionTable.setItems(movieSessions);
    }

    movie.setCellValueFactory(new PropertyValueFactory<>("movie"));
    movie.setStyle("-fx-alignment: CENTER;");

    cinemaHall.setCellValueFactory(new PropertyValueFactory<>("cinemaHall"));
    cinemaHall.setStyle("-fx-alignment: CENTER;");

    startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    startTime.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteMovieSession));
  }

  /**
   * Deletes a movie session.
   *
   * @param movieSession the movie session to be deleted
   */
  private void deleteMovieSession(MovieSessionDTO movieSession) {
    showConfirmationDialog(movieSession);
  }

  /**
   * Displays a confirmation dialog for deleting a movie session.
   *
   * @param movieSession The movie session to be deleted.
   */
  private void showConfirmationDialog(MovieSessionDTO movieSession) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText(null);
    alert.setContentText("Deseja realmente excluir a sessão do filme " + movieSession.getMovie() + " no horário de "
        + movieSession.getStartTime() + " na sala " + movieSession.getCinemaHall() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        DeleteMovieSessionDTO deleteMovieSessionDTO = new DeleteMovieSessionDTO(movieSession.getID());

        Response<?> deleteResponse = DeleteMovieSessionFactory.make().handle(deleteMovieSessionDTO);

        if (deleteResponse.getStatusCode() == 204) {
          new AlertSuccess("Sessão deletada com sucesso!");
          movieSessionTable.getItems().remove(movieSession);
        } else {
          new AlertError(deleteResponse.getData().toString());
        }
      }
    });

  }

  /**
   * Creates a new movie session.
   *
   * @param event the MouseEvent that triggered the method
   * @throws Exception if an error occurs while creating the session
   */
  @FXML
  void createSession(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovieSession.fxml");
  }

}
