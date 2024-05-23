package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

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
    private MenuItem registerGenreMenuItem;

    @FXML
    void registerGenre(ActionEvent event) throws Exception {
        new ChangeWindow("/com/cinema/main/views/movies/createGenre.fxml", event);
    }

}
