package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.CreateCinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.CreateCinemaHallFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateCinemaHallView {

    @FXML
    private TextField capacity;

    @FXML
    private Button createCinemaHallButton;

    @FXML
    private TextField name;

    @FXML
    void createCinemaHall(ActionEvent event) {
        CreateCinemaHallDTO createCinemaHallDTO = new CreateCinemaHallDTO(Integer.parseInt(this.capacity.getText()),
                this.name.getText());

        Response<?> response = CreateCinemaHallFactory.make().handle(createCinemaHallDTO);

        if (response.getStatusCode() == 204) {
            new AlertSuccess("Sala de cinema criada com sucesso!");
        } else {
            new AlertError(response.getData().toString());
        }
    }

}
