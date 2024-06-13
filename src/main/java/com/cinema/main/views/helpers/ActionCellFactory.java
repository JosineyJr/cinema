package com.cinema.main.views.helpers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionCellFactory<T> implements Callback<TableColumn<T, Void>, TableCell<T, Void>> {

  private final ActionHandler<T> deleteHandler;
  private final ExceptionThrowingActionHandler<T> editHandler;

  public ActionCellFactory(ActionHandler<T> deleteHandler, ExceptionThrowingActionHandler<T> editHandler) {
    this.deleteHandler = deleteHandler;
    this.editHandler = editHandler;
  }

  @Override
  public TableCell<T, Void> call(TableColumn<T, Void> param) {
    return new TableCell<>() {
      private final Button deleteButton = new Button("Excluir");
      private final Button editButton = new Button("Editar");

      {
        deleteButton.setOnAction(event -> deleteHandler.handle(getTableView().getItems().get(getIndex())));
        editButton.setOnAction(event -> {
          try {
            editHandler.handle(getTableView().getItems().get(getIndex()));
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
      }

      @Override
      protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
        } else {
          HBox buttons = new HBox(deleteButton, editButton);
          buttons.setSpacing(5);
          buttons.setStyle("-fx-alignment: CENTER;");
          setGraphic(buttons);
        }
      }
    };
  }
}
