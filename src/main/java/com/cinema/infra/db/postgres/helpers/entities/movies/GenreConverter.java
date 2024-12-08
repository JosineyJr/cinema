package com.cinema.infra.db.postgres.helpers.entities.movies;

import com.cinema.domain.entities.movies.Genre;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class GenreConverter implements IEntityConverter<PgGenre, Genre> {

  @Override
  public Genre convert(PgGenre source) {
    return new Genre(source.getID(), source.getName());
  }

  @Override
  public PgGenre pgConverter(Genre target) {
    return new PgGenre(target.getID(), target.getName());
  }
}
