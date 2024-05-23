package com.cinema.main.views.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeWindow {

  public static void changeScene(Stage stage, String fxmlFilePath) throws Exception {
    Parent root = FXMLLoader.load(ChangeWindow.class.getResource(fxmlFilePath));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}