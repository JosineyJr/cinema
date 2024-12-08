package com.cinema.application.errors;

public class MinDoubleValueError extends Exception {
  public MinDoubleValueError(String fieldName, double minValue) {
    super("O campo " + fieldName + " deve ser maior ou igual a " + minValue);
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
