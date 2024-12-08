package com.cinema.domain.errors.movies;

public class MovieSessionNotFoundError extends Exception {
  public MovieSessionNotFoundError() {
    super("Sessão não encontrada");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
