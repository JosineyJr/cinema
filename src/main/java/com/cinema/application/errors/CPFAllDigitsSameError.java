package com.cinema.application.errors;

public class CPFAllDigitsSameError extends Exception {
  public CPFAllDigitsSameError() {
    super("CPF cannot have all digits the same");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
