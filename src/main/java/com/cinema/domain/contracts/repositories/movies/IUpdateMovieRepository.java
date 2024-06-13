package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.Movie;

public interface IUpdateMovieRepository {
  void updateMovie(Movie movie);
}
