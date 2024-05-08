package com.cinema.main;

import java.util.ArrayList;

import com.cinema.application.controllers.users.CreateClientController;
import com.cinema.application.dtos.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.usecases.users.CreateClientUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.PgClientRepository;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    public static void main(String[] args) {
        new PgConnection().connect();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cinema/main/views/users/CreateClient.fxml"));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Cadastrar Cliente");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
