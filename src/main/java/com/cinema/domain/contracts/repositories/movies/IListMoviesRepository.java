package com.cinema.domain.contracts.repositories.movies;

import java.util.List;

import com.cinema.domain.entities.movies.Movie;

public interface IListMoviesRepository {
  public List<Movie> listMovies();
}
