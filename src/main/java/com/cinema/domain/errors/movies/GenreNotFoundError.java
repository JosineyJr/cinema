package com.cinema.domain.errors.movies;

public class GenreNotFoundError extends Exception {
  public GenreNotFoundError() {
    super("Gênero não encontrado.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
