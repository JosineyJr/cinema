package com.cinema.application.errors;

public class ValidateLocalDateTimeError extends Exception {
  public ValidateLocalDateTimeError(String fieldName) {
    super("O campo " + fieldName + " não é uma data válida");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
