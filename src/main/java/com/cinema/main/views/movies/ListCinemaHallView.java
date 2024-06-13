package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.CinemaHallDTO;
import com.cinema.application.dtos.movies.DeleteCinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.DeleteCinemaHallFactory;
import com.cinema.main.factories.movies.ListCinemaHallsFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
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
 * This class represents the view for listing cinema halls.
 * It displays a table with cinema hall information and provides functionality
 * to create and delete cinema halls.
 */
public class ListCinemaHallView {

  @FXML
  private TableColumn<CinemaHallDTO, Void> action;

  @FXML
  private TableColumn<CinemaHallDTO, String> capacity;

  @FXML
  private TableView<CinemaHallDTO> cinemaHallTable;

  @FXML
  private TableColumn<CinemaHallDTO, String> name;

  /**
   * Initializes the ListCinemaHallView.
   * This method is automatically called by JavaFX after the FXML file has been
   * loaded.
   * It retrieves a list of cinema halls and populates the cinemaHallTable with
   * the data.
   * It also sets up the cell value factories and cell factories for the table
   * columns.
   */
  @FXML
  void initialize() {
    Response<?> response = ListCinemaHallsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<CinemaHallDTO> cinemaHalls = FXCollections.observableArrayList();

      for (Object cinemaHall : (List<?>) data) {
        if (cinemaHall instanceof CinemaHallDTO) {
          cinemaHalls.add((CinemaHallDTO) cinemaHall);
        }
      }

      cinemaHallTable.setItems(cinemaHalls);
    }

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    name.setStyle("-fx-alignment: CENTER;");

    capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    capacity.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(new ActionCellFactory<>(this::editCinemaHall, this::deleteCinemaHall));

  }

  /**
   * Event handler for the createCinemaHall button.
   * This method is called when the button is clicked and is responsible for
   * creating a new cinema hall.
   * It opens a new window to create a cinema hall using the createCinemaHall.fxml
   * file.
   *
   * @param event The mouse event that triggered the method.
   * @throws Exception If an error occurs while creating the cinema hall.
   */
  @FXML
  void createCinemaHall(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createCinemaHall.fxml");
  }

  /**
   * Deletes a cinema hall.
   *
   * @param cinemaHall the cinema hall to be deleted
   */
  private void deleteCinemaHall(CinemaHallDTO cinemaHall) {
    showConfirmationDialog(cinemaHall);
  }

  /**
   * Displays a confirmation dialog for deleting a cinema hall.
   *
   * @param cinemaHall The CinemaHallDTO object representing the cinema hall to be
   *                   deleted.
   */
  private void showConfirmationDialog(CinemaHallDTO cinemaHall) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText(null);
    alert.setContentText("Você realmente deseja excluir a sala de cinema '" + cinemaHall.getName() + "'?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        DeleteCinemaHallDTO deleteCinemaHallDTO = new DeleteCinemaHallDTO(cinemaHall.getID());

        Response<?> deleteResponse = DeleteCinemaHallFactory.make().handle(deleteCinemaHallDTO);

        if (deleteResponse.getStatusCode() == 204) {
          new AlertSuccess("Sala de cinema deletada com sucesso!");
          cinemaHallTable.getItems().remove(cinemaHall);
        } else {
          new AlertError(deleteResponse.getData().toString());
        }
      }
    });
  }

  private void editCinemaHall(CinemaHallDTO cinemaHall) throws Exception {
    CinemaHallModel.getInstance().setCinemaHall(cinemaHall);

    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/editCinemaHall.fxml");
  }
}
