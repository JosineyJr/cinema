package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.DeleteGenreDTO;
import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.DeleteGenreFactory;
import com.cinema.main.factories.movies.ListGenresFactory;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents the view for listing genres in a cinema application.
 * It displays a table of genres with the option to delete a genre.
 */
public class ListGenresView {

  @FXML
  private TableView<GenreDTO> genreTable;

  @FXML
  private TableColumn<GenreDTO, String> nameColumn;

  @FXML
  private TableColumn<GenreDTO, Void> actionColumn;

  @FXML
  private Label createGenreLabel;

  /**
   * Initializes the ListGenresView.
   * This method is automatically called after the FXML file has been loaded.
   * It retrieves the list of genres from the server and populates the genreTable with the data.
   * It also sets up the cell factory for the actionColumn to display a delete button for each genre.
   */
  @FXML
  void initialize() {
    Response<?> response = ListGenresFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<GenreDTO> genres = FXCollections.observableArrayList();

      for (Object genre : (List<?>) data) {
        if (genre instanceof GenreDTO) {
          genres.add((GenreDTO) genre);
        }
      }

      genreTable.setItems(genres);
    }

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    nameColumn.setStyle("-fx-alignment: CENTER;");

    actionColumn.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteGenre));
  }

  /**
   * Deletes the specified genre.
   *
   * @param genre the genre to be deleted
   */
  private void deleteGenre(GenreDTO genre) {
    showConfirmationDialog(genre);
  }

  /**
   * Displays a confirmation dialog for deleting a genre.
   *
   * @param genre The GenreDTO object representing the genre to be deleted.
   */
  private void showConfirmationDialog(GenreDTO genre) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText(null);
    alert.setContentText("Você realmente deseja excluir o gênero '" + genre.getName() + "'?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        DeleteGenreDTO deleteGenreDTO = new DeleteGenreDTO(genre.getID());

        Response<?> deleteResponse = DeleteGenreFactory.make().handle(deleteGenreDTO);

        if (deleteResponse.getStatusCode() == 204) {
          new AlertSuccess("Gênero deletado com sucesso!");
          genreTable.getItems().remove(genre);
        } else {
          new AlertError(deleteResponse.getData().toString());
        }
      }
    });
  }

  /**
   * Event handler for creating a new genre.
   * This method is called when the user clicks on a button to create a new genre.
   * It opens a new window to create a genre using the createGenre.fxml file.
   *
   * @param event The mouse event that triggered the method.
   * @throws Exception If an error occurs while creating the genre.
   */
  @FXML
  void createGenre(MouseEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createGenre.fxml");
  }

}
