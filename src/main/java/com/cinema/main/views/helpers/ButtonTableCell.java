package com.cinema.main.views.helpers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import java.util.function.Consumer;

public class ButtonTableCell<S, T> extends TableCell<S, T> {
  private final Button button;
  private final Consumer<S> onAction;

  public ButtonTableCell(String label, Consumer<S> onAction) {
    this.button = new Button(label);
    this.onAction = onAction;

    this.button.setOnAction(event -> {
      if (onAction != null) {
        onAction.accept(getTableRow().getItem());
      }
    });
  }

  @Override
  protected void updateItem(T item, boolean empty) {
    super.updateItem(item, empty);

    if (empty) {
      setGraphic(null);
    } else {
      setGraphic(button);
      setStyle("-fx-alignment: CENTER;");
    }
  }
}
