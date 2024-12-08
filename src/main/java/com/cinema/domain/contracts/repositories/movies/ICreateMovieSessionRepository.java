package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.MovieSession;

public interface ICreateMovieSessionRepository {
  public UUID createMovieSession(MovieSession movieSession);
}
