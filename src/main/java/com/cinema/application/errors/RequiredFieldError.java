package com.cinema.application.errors;

public class RequiredFieldError extends Exception {
  public RequiredFieldError(String field) {
    super("O campo " + field + " é obrigatório.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
