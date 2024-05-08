package com.cinema.application.errors;

public class InvalidCPFError extends Exception {
  public InvalidCPFError() {
    super("Invalid CPF");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
