package com.cinema.domain.errors.movies;

public class CinemaHallAlreadyExistsError extends Exception {
  public CinemaHallAlreadyExistsError() {
    super("Já existe uma sala de cinema com esse nome");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
