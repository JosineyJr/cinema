package com.cinema.application.errors;

public class ServerError extends Throwable {
  public ServerError(Throwable error) {
    super("Erro interno no servidor. Tente novamente mais tarde.");

    if (error != null) {
      this.setStackTrace(error.getStackTrace());
    }
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
