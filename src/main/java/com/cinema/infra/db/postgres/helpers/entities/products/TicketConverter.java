package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Ticket;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.MovieSessionConverter;

public class TicketConverter implements IEntityConverter<PgTicket, Ticket> {
  private MovieSessionConverter movieSessionConverter = new MovieSessionConverter();

  @Override
  public Ticket convert(PgTicket source) {
    return new Ticket(source.getID(), source.getPrice(),
        this.movieSessionConverter.convert(source.getMovieSession()));
  }

  @Override
  public PgTicket pgConverter(Ticket target) {
    return new PgTicket(target.getID(), target.getPrice(),
        this.movieSessionConverter.pgConverter(target.getMovieSession()));
  }
}
