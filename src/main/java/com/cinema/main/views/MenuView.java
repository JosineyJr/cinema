package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
    private Parent root;

    @FXML
    void registerCinemaHall(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();
        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createCinemaHall.fxml");
    }

    @FXML
    void registerGenre(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();
        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createGenre.fxml");
    }

    @FXML
    void registerMovie(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();
        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovie.fxml");
    }

    @FXML
    void registerMovieSession(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();
        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovieSession.fxml");
    }

}
