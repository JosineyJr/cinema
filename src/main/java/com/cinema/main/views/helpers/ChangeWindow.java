package com.cinema.main.views.helpers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangeWindow {
  public ChangeWindow(String path, MouseEvent event) throws Exception {
    this.changeWindow(path, (Event) event);
  }

  public ChangeWindow(String path, ActionEvent event) throws Exception {
    this.changeWindow(path, event);
  }

  public void changeWindow(String path, Event event) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));

    Parent clientMoviesMenu = fxmlLoader.load();

    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(clientMoviesMenu);

    currentWindow.setScene(scene);
    currentWindow.show();
  }
}
