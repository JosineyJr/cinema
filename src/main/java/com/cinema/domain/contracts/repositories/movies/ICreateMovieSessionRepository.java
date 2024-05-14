package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.MovieSession;

public interface ICreateMovieSessionRepository {
  public void createMovieSession(MovieSession movieSession);
}
