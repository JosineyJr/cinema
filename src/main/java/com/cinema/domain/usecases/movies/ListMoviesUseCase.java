package com.cinema.domain.usecases.movies;

import java.util.List;

import com.cinema.domain.contracts.repositories.movies.IListMoviesRepository;
import com.cinema.domain.entities.movies.Movie;

public class ListMoviesUseCase {
  private IListMoviesRepository moviesRepository;

  public ListMoviesUseCase(IListMoviesRepository moviesRepository) {
    this.moviesRepository = moviesRepository;
  }

  /**
   * Executes the use case to list all movies.
   *
   * @return a list of movies.
   */
  public List<Movie> execute() {
    return moviesRepository.listMovies();
  }
}
