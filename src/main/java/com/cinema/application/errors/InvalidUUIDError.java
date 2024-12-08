package com.cinema.application.errors;

public class InvalidUUIDError extends Exception {
  public InvalidUUIDError(String fieldName) {
    super("O campo " + fieldName + " não é um UUID válido");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
