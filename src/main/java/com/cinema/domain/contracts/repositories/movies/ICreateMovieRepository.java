package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.Movie;

public interface ICreateMovieRepository {
  public void createMovie(Movie movie);
}
