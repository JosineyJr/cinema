package com.cinema.domain.errors.movies;

public class CinemaHallNotFoundError extends Exception {
  public CinemaHallNotFoundError() {
    super("Sala de cinema não encontrada");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
