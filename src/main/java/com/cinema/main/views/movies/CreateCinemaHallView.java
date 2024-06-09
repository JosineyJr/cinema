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

/**
 * This class represents the view for creating a cinema hall.
 * It contains the necessary fields and methods for creating a cinema hall.
 */
public class CreateCinemaHallView {

    @FXML
    private TextField capacity;

    @FXML
    private Button createCinemaHallButton;

    @FXML
    private TextField name;

    /**
     * Handles the action event when the "Create Cinema Hall" button is clicked.
     * Creates a new cinema hall based on the input values provided by the user.
     * Displays a success message if the cinema hall is created successfully,
     * otherwise displays an error message with the response data.
     *
     * @param event the action event triggered by clicking the button
     */
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
