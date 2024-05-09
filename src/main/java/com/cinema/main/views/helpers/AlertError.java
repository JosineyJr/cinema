package com.cinema.main.views.helpers;

import javafx.scene.control.Alert;

public class AlertError {
  public AlertError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(message);
    alert.showAndWait();
  }
}
