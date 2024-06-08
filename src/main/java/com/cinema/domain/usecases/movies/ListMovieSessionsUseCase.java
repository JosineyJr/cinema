package com.cinema.domain.usecases.movies;

import java.util.List;

import com.cinema.domain.contracts.repositories.movies.IListMovieSessionsRepository;
import com.cinema.domain.entities.movies.MovieSession;

public class ListMovieSessionsUseCase {
  private IListMovieSessionsRepository movieSessionsRepository;

  public ListMovieSessionsUseCase(IListMovieSessionsRepository movieSessionsRepository) {
    this.movieSessionsRepository = movieSessionsRepository;
  }

  public List<MovieSession> execute() {
    return movieSessionsRepository.listMovieSessions();
  }
}
