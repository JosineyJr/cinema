package com.cinema.domain.errors.users;

public class PersonNotFoundError extends Exception {
  public PersonNotFoundError() {
    super("Usuário não encontrado.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
