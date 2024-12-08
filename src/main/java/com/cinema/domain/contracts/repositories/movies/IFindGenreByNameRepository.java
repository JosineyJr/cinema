package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.Genre;

public interface IFindGenreByNameRepository {
  public Genre findGenreByName(String genre);
}
