package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

public interface IDeleteMovieRepository {
  public void deleteMovie(UUID ID);
}
