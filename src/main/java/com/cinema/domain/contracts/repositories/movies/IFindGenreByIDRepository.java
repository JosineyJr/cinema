package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.Genre;

public interface IFindGenreByIDRepository {
  public Genre findGenreByID(UUID ID);
}
