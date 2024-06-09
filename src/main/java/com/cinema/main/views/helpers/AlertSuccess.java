package com.cinema.main.views.helpers;

import javafx.scene.control.Alert;

/**
 * A helper class for displaying success alerts.
 */
public class AlertSuccess {
  /**
   * Constructs a new AlertSuccess object with the specified message.
   *
   * @param message the message to be displayed in the alert
   */
  public AlertSuccess(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sucesso");
    alert.setHeaderText(message);
    alert.showAndWait();
  }
}
