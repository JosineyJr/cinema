package com.cinema.application.errors;

public class RequiredFieldError extends Exception {
  public RequiredFieldError(String field) {
    super("Field " + field + " is required");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
