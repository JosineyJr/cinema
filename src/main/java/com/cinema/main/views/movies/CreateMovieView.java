package com.cinema.main.views.movies;

import java.util.List;

import com.cinema.application.dtos.movies.CreateMovieDTO;
import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.CreateMovieFactory;
import com.cinema.main.factories.movies.ListGenresFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateMovieView {

    @FXML
    private Button createMovieButton;

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

    @FXML
    void initialize() {
        Response<?> response = ListGenresFactory.make().handle(null);

        Object data = response.getData();

        if (data instanceof List) {
            for (Object genre : (List<?>) data) {
                if (genre instanceof GenreDTO) {
                    GenreDTO genreDTO = (GenreDTO) genre;

                    this.genre.getItems().add(new Item(genreDTO.getID(), genreDTO.getName()));
                }
            }
        }
    }

    @FXML
    void createMovie(ActionEvent event) {
        String genreID = this.genre.getSelectionModel().getSelectedItem().getID().toString();

        CreateMovieDTO createMovieDTO = new CreateMovieDTO(this.title.getText(),
                this.synopsis.getText(),
                this.director.getText(), genreID, Integer.parseInt(this.duration.getText()),
                Integer.parseInt(this.minimunAge.getText()));

        Response<?> response = CreateMovieFactory.make().handle(createMovieDTO);

        if (response.getStatusCode() == 204) {
            new AlertSuccess("Filme criado com sucesso");
        } else {
            new AlertError(response.getData().toString());
        }
    }

}
