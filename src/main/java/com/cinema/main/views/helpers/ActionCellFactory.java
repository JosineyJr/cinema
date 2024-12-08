package com.cinema.main.views.helpers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionCellFactory<T> implements Callback<TableColumn<T, Void>, TableCell<T, Void>> {

  private final ActionHandler<T> deleteHandler;
  private final ExceptionThrowingActionHandler<T> editHandler;
  private final String deleteButtonText;
  private final String editButtonText;

  public ActionCellFactory(ExceptionThrowingActionHandler<T> editHandler, ActionHandler<T> deleteHandler,
      String editButtonText, String deleteButtonText) {
    this.deleteHandler = deleteHandler;
    this.editHandler = editHandler;
    this.deleteButtonText = deleteButtonText;
    this.editButtonText = editButtonText;
  }

  @Override
  public TableCell<T, Void> call(TableColumn<T, Void> param) {
    return new TableCell<>() {
      private final Button deleteButton = new Button(deleteButtonText);
      private final Button editButton = new Button(editButtonText);

      {
        deleteButton.setOnAction(event -> deleteHandler.handle(getTableView().getItems().get(getIndex())));
        editButton.setOnAction(event -> {
          try {
            editHandler.handle(getTableView().getItems().get(getIndex()));
          } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            // You might also want to show an alert to the user
          }
        });
      }

      @Override
      protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
        } else {
          HBox buttons = new HBox(editButton, deleteButton);
          buttons.setSpacing(5);
          buttons.setStyle("-fx-alignment: CENTER;");
          setGraphic(buttons);
        }
      }
    };
  }
}