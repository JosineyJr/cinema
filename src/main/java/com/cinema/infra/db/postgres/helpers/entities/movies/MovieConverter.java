package com.cinema.infra.db.postgres.helpers.entities.movies;

import com.cinema.domain.entities.movies.Movie;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class MovieConverter implements IEntityConverter<PgMovie, Movie> {
  private GenreConverter genreConverter = new GenreConverter();

  @Override
  public Movie convert(PgMovie source) {
    return new Movie(source.getID(), source.getTitle(), source.getSynopsis(), source.getDirector(),
        this.genreConverter.convert(source.getGenre()), source.getDuration(), source.getMinimumAge());
  }

  @Override
  public PgMovie pgConverter(Movie target) {
    return new PgMovie(target.getID(), target.getTitle(), target.getSynopsis(), target.getDirector(),
        this.genreConverter.pgConverter(target.getGenre()), target.getDuration(), target.getMinimumAge());
  }
}
