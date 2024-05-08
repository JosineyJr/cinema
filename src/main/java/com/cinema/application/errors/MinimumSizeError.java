package com.cinema.application.errors;

public class MinimumSizeError extends Exception {
  public MinimumSizeError(String fieldName, int minimumSize) {
    super(fieldName + " must have at least " + minimumSize + " characters");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
