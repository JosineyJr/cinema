package com.cinema.infra.db.postgres.helpers.entities.movies;

import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class MovieSessionConverter implements IEntityConverter<PgMovieSession, MovieSession> {
  private MovieConverter movieConverter = new MovieConverter();
  private CinemaHallConverter cinemaHallConverter = new CinemaHallConverter();

  @Override
  public MovieSession convert(PgMovieSession source) {
    return new MovieSession(source.getID(), movieConverter.convert(source.getMovie()),
        cinemaHallConverter.convert(source.getCinemaHall()),
        source.getStartDate());
  }

  @Override
  public PgMovieSession pgConverter(MovieSession target) {
    return new PgMovieSession(target.getID(), movieConverter.pgConverter(target.getMovie()),
        cinemaHallConverter.pgConverter(target.getCinemaHall()),
        target.getStartDate());
  }
}
