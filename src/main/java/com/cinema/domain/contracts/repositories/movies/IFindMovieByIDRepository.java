package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.Movie;

public interface IFindMovieByIDRepository {
  public Movie findMovieByID(UUID ID);
}
