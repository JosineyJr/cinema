package com.cinema.domain.errors;

public class LoginError extends Exception {
  public LoginError() {
    super("CPF/Senha inválidos");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
