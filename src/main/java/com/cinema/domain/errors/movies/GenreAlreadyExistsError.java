package com.cinema.domain.errors.movies;

public class GenreAlreadyExistsError extends Exception {
  public GenreAlreadyExistsError() {
    super("O gênero já existe!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
