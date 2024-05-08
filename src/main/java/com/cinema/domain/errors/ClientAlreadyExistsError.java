package com.cinema.domain.errors;

public class ClientAlreadyExistsError extends Exception {
  public ClientAlreadyExistsError() {
    super("Cliente já cadastrado.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
