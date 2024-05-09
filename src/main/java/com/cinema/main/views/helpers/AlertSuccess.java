package com.cinema.main.views.helpers;

import javafx.scene.control.Alert;

public class AlertSuccess {
    public AlertSuccess(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sucesso");
    alert.setHeaderText(message);
    alert.showAndWait();
  }
}
