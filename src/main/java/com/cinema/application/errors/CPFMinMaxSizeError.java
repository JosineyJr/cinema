package com.cinema.application.errors;

public class CPFMinMaxSizeError extends Exception {
  public CPFMinMaxSizeError() {
    super("CPF must have 11 characters");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
