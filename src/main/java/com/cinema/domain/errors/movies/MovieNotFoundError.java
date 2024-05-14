package com.cinema.domain.errors.movies;

public class MovieNotFoundError extends Exception {
  public MovieNotFoundError() {
    super("Filme não encontrado");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
