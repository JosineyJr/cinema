package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.ListGenresFactory;
import com.cinema.main.views.StageManager;
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

public class ListGenresView {

    @FXML
    private TableView<GenreDTO> genreTable;

    @FXML
    private TableColumn<GenreDTO, String> nameColumn;

    @FXML
    private TableColumn<GenreDTO, Void> actionColumn;

    @FXML
    private Label createGenreLabel;

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

        // Configurando a fábrica de células para a coluna 'name'
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment: CENTER;");

        actionColumn.setCellFactory(column -> new ButtonTableCell<>("Excluir", this::deleteGenre));
    }

    private void deleteGenre(GenreDTO genre) {
        showConfirmationDialog(genre);
    }

    private void showConfirmationDialog(GenreDTO genre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir o gênero '" + genre.getName() + "'?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // genreTable.getItems().remove(genre);
                // Aqui você pode adicionar a lógica para remover o item da fonte de dados real
            }
        });
    }

    @FXML
    void createGenre(MouseEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();

        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createGenre.fxml");
    }

}
