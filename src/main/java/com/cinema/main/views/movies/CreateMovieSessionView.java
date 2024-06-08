package com.cinema.main.views.movies;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javafx.beans.value.ChangeListener;

import com.cinema.application.dtos.movies.CinemaHallDTO;
import com.cinema.application.dtos.movies.CreateMovieSessionDTO;
import com.cinema.application.dtos.movies.MovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.CreateMovieSessionFactory;
import com.cinema.main.factories.movies.ListCinemaHallsFactory;
import com.cinema.main.factories.movies.ListMoviesFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.Item;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateMovieSessionView {

    @FXML
    private ChoiceBox<Item> cinemaHall;

    @FXML
    private Button createMovieSessionButton;

    @FXML
    private ChoiceBox<Item> movie;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField startTime;

    @FXML
    private TextField ticketPrice;

    @FXML
    void initialize() {
        Response<?> moviesResponse = ListMoviesFactory.make().handle(null);

        Object moviesData = moviesResponse.getData();

        if (moviesData instanceof List) {
            for (Object movie : (List<?>) moviesData) {
                if (movie instanceof MovieDTO) {
                    MovieDTO movieDTO = (MovieDTO) movie;

                    this.movie.getItems().add(new Item(movieDTO.getID(), movieDTO.getTitle()));
                }
            }
        }

        Response<?> cinemaHallsResponse = ListCinemaHallsFactory.make().handle(null);

        Object cinemaHallsData = cinemaHallsResponse.getData();

        if (cinemaHallsData instanceof List) {
            for (Object cinemaHall : (List<?>) cinemaHallsData) {
                if (cinemaHall instanceof CinemaHallDTO) {
                    CinemaHallDTO cinemaHallDTO = (CinemaHallDTO) cinemaHall;

                    this.cinemaHall.getItems().add(new Item(cinemaHallDTO.getID(), cinemaHallDTO.getName()));
                }
            }
        }

        startTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty() && !newValue.matches("[0-9:]*")) {
                    startTime.setText(oldValue);
                } else if (newValue.length() == 2 || newValue.length() == 6) {
                    startTime.setText(newValue + ":");
                } else if (newValue.length() >= 5) {
                    if (!isValidTime(newValue)) {
                        startTime.setText(oldValue);
                    }
                }
            }
        });
    }

    @FXML
    void createMovieSession(ActionEvent event) {
        String cinemaHallID = this.cinemaHall.getSelectionModel().getSelectedItem().getID().toString();
        String movieID = this.movie.getSelectionModel().getSelectedItem().getID().toString();
        LocalDate startDate = this.startDate.getValue();
        String startTime = this.startTime.getText();
        double ticketPrice = Double.parseDouble(this.ticketPrice.getText());

        String sessionDateTime = LocalDateTime.of(startDate,
                LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"))).toString();

        Response<?> response = CreateMovieSessionFactory.make()
                .handle(new CreateMovieSessionDTO(movieID, cinemaHallID,
                        sessionDateTime,
                        ticketPrice));

        if (response.getStatusCode() == 204) {
            new AlertSuccess("Sessão criada com sucesso");
        } else {
            new AlertError(response.getData().toString());
        }
    }

    private boolean isValidTime(String time) {
        if (time.isEmpty()) {
            return true; // Permite limpar o campo
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(time, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
