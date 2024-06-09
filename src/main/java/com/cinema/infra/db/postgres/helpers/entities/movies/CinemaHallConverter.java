package com.cinema.infra.db.postgres.helpers.entities.movies;

import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class CinemaHallConverter implements IEntityConverter<PgCinemaHall, CinemaHall> {

  @Override
  public CinemaHall convert(PgCinemaHall source) {
    return new CinemaHall(source.getID(), source.getCapacity(), source.getName());
  }

  @Override
  public PgCinemaHall pgConverter(CinemaHall target) {
    return new PgCinemaHall(target.getID(), target.getCapacity(), target.getName());
  }
}
