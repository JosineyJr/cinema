package com.cinema.application.errors;

public class MinValueError extends Exception {
  public MinValueError(String fieldName, int minValue) {
    super("O campo " + fieldName + " deve ser maior ou igual a " + minValue);
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
