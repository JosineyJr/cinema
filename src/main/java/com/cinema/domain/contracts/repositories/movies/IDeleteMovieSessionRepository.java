package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

public interface IDeleteMovieSessionRepository {
  public void deleteMovieSession(UUID ID);
}
