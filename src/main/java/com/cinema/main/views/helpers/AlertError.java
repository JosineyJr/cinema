package com.cinema.main.views.helpers;

import javafx.scene.control.Alert;

/**
 * The AlertError class represents an error alert that displays a message to the
 * user.
 */
public class AlertError {

  /**
   * Constructs a new AlertError object with the specified error message.
   * 
   * @param message the error message to be displayed in the alert
   */
  public AlertError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(message);
    alert.showAndWait();
  }
}
