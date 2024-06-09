package com.cinema.main.views;

import javafx.stage.Stage;

/**
 * The StageManager class is responsible for managing the primary stage of the application.
 * It provides methods to set and get the primary stage.
 */
public class StageManager {
  private static Stage primaryStage;

  /**
   * Sets the primary stage of the application.
   * @param stage The primary stage to be set.
   */
  public static void setPrimaryStage(Stage stage) {
    primaryStage = stage;
  }

  /**
   * Gets the primary stage of the application.
   * @return The primary stage.
   */
  public static Stage getPrimaryStage() {
    return primaryStage;
  }
}
