package com.cinema.domain.errors;

public class EmployeeAlreadyExistsError extends Exception {
  public EmployeeAlreadyExistsError() {
    super("O funcionário já foi cadastrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
