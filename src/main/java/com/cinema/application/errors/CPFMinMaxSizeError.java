package com.cinema.application.errors;

public class CPFMinMaxSizeError extends Exception {
  public CPFMinMaxSizeError() {
    super("CPF deve ter 11 dígitos.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
