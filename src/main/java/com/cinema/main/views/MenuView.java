package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuView {

    @FXML
    private Menu cart;

    @FXML
    private Menu management;

    @FXML
    private Menu movies;

    @FXML
    private Menu products;

    @FXML
    private Menu profile;

    @FXML
    private MenuItem registerCinemaHallMenuItem;

    @FXML
    private MenuItem registerGenreMenuItem;

    @FXML
    private MenuItem registerMovieMenuItem;

    @FXML
    private MenuItem registerMovieSessionMenuItem;

    @FXML
    void registerCinemaHall(ActionEvent event) {

    }

    @FXML
    void registerGenre(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ChangeWindow.changeScene(stage, "/com/cinema/main/views/movies/createGenre.fxml");
    }

    @FXML
    void registerMovie(ActionEvent event) {

    }

    @FXML
    void registerMovieSession(ActionEvent event) {

    }

}
