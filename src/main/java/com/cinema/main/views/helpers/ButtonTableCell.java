package com.cinema.main.views.helpers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import java.util.function.Consumer;

/**
 * A custom TableCell implementation that displays a button in each cell.
 *
 * @param <S> The type of the TableView's items.
 * @param <T> The type of the cell's value.
 */
public class ButtonTableCell<S, T> extends TableCell<S, T> {
  private final Button button;
  private final Consumer<S> onAction;

  /**
   * Constructs a new ButtonTableCell with the specified label and action.
   *
   * @param label    The label to be displayed on the button.
   * @param onAction The action to be performed when the button is clicked.
   */
  public ButtonTableCell(String label, Consumer<S> onAction) {
    this.button = new Button(label);
    this.onAction = onAction;

    this.button.setOnAction(event -> {
      if (onAction != null) {
        onAction.accept(getTableRow().getItem());
      }
    });
  }

  /**
   * Updates the item in the cell and sets the graphic representation accordingly.
   *
   * @param item  The new item to be displayed in the cell.
   * @param empty A boolean indicating whether the cell is empty or not.
   */
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
