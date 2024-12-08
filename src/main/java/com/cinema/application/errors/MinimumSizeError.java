package com.cinema.application.errors;

public class MinimumSizeError extends Exception {
  public MinimumSizeError(String fieldName, int minimumSize) {
    super(fieldName + " deve ter no mínimo " + minimumSize + " caracteres.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
