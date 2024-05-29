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

public class CreateGenreView {

    @FXML
    private TextField genreNameField;

    @FXML
    private Button registerButton;

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
