package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.Genre;

public interface ICreateGenreRepository {
  public void createGenre(Genre genre);
}
