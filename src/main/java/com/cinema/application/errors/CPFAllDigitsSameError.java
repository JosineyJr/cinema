package com.cinema.application.errors;

public class CPFAllDigitsSameError extends Exception {
  public CPFAllDigitsSameError() {
    super("CPF não pode ter todos os dígitos iguais.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
