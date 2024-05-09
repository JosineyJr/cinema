package com.cinema.main;

import com.cinema.infra.db.postgres.helpers.PgConnection;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    public static void main(String[] args) {
        PgConnection.getInstance().connect();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cinema/main/views/auth/login.fxml"));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Sistema de Cinema");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
