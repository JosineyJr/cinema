package com.cinema.main.views.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The ChangeWindow class provides a utility method to change the scene in a JavaFX stage.
 */
public class ChangeWindow {

  /**
   * Changes the scene in the specified stage to the one defined by the given FXML file path.
   *
   * @param stage         the JavaFX stage to change the scene in
   * @param fxmlFilePath  the file path of the FXML file defining the new scene
   * @throws Exception    if an error occurs while loading the FXML file or setting the new scene
   */
  public static void changeScene(Stage stage, String fxmlFilePath) throws Exception {
    Parent root = FXMLLoader.load(ChangeWindow.class.getResource(fxmlFilePath));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}