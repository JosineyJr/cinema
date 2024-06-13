package com.cinema.main.views.helpers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CellValueFactoryUtil {

  public static <T> Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>> createCellValueFactory(
      ValueExtractor<T> extractor) {
    return cellData -> {
      T item = cellData.getValue();
      String value = extractor.extract(item);
      return new SimpleStringProperty(value);
    };
  }

  @FunctionalInterface
  public interface ValueExtractor<T> {
    String extract(T item);
  }
}