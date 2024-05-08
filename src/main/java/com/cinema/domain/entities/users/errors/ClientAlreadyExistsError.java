package com.cinema.domain.entities.users.errors;

public class ClientAlreadyExistsError extends Exception {
  public ClientAlreadyExistsError() {
    super("Client already exists");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
