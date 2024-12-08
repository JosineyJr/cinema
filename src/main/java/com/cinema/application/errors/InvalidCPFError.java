package com.cinema.application.errors;

public class InvalidCPFError extends Exception {
  public InvalidCPFError() {
    super("CPF inválido.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
