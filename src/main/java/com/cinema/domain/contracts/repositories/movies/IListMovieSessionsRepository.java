package com.cinema.domain.contracts.repositories.movies;

import java.util.List;

import com.cinema.domain.entities.movies.MovieSession;

public interface IListMovieSessionsRepository {
  public List<MovieSession> listMovieSessions();
}
