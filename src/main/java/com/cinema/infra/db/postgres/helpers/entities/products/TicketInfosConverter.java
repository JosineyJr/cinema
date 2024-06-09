package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.infra.db.postgres.entities.products.PgTicketInfos;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.MovieSessionConverter;

public class TicketInfosConverter implements IEntityConverter<PgTicketInfos, TicketInfos> {
  private MovieSessionConverter movieSessionConverter = new MovieSessionConverter();

  @Override
  public TicketInfos convert(PgTicketInfos source) {
    return new TicketInfos(source.getID(), source.getPrice(),
        this.movieSessionConverter.convert(source.getMovieSession()));
  }

  @Override
  public PgTicketInfos pgConverter(TicketInfos target) {
    return new PgTicketInfos(target.getID(), target.getPrice(),
        this.movieSessionConverter.pgConverter(target.getMovieSession()));
  }
}
