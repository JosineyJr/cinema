package com.cinema.domain.errors.movies;

public class MovieSessionAlreadyScreeningInCinemaHallError extends Exception {
  public MovieSessionAlreadyScreeningInCinemaHallError() {
    super("Uma sessão do filme já está sendo exibida nesta sala de cinema");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
