package com.cinema.main;

import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.main.views.StageManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * The main class of the cinema application.
 * This class extends the Application class and is responsible for launching the
 * application.
 */
public class Main extends Application {
    /**
     * The main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PgConnection.getInstance();

        launch(args);
    }

    /**
     * This method is called when the application is launched. It sets up the main
     * stage
     * and displays the user interface for the cinema system.
     *
     * @param primaryStage the primary stage of the application
     * @throws Exception if an error occurs during the initialization of the stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/cinema/main/views/sales/listProductsToSale.fxml"));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Sistema de Cinema");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        StageManager.setPrimaryStage(primaryStage);
    }
}
