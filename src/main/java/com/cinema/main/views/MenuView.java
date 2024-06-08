package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuView {

    @FXML
    void listGenres(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();
        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listGenres.fxml");
    }

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

    @FXML
    void listCinemaHalls(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();

        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listCinemaHalls.fxml");
    }

    @FXML
    void listMovieSession(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();

        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listMovieSessions.fxml");
    }

    @FXML
    void listMovies(ActionEvent event) throws Exception {
        Stage primaryStage = StageManager.getPrimaryStage();

        ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listMovies.fxml");
    }

}
