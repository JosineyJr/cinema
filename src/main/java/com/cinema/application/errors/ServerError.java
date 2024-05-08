package com.cinema.application.errors;

public class ServerError extends Exception {
  public ServerError(Exception error) {
    super("Server failed. Try again soon");

    if (error != null) {
      this.setStackTrace(error.getStackTrace());
    }
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
