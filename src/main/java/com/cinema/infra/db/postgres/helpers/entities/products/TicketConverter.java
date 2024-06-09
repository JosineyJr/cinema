package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Ticket;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class TicketConverter implements IEntityConverter<PgTicket, Ticket> {
  private TicketInfosConverter ticketInfosConverter = new TicketInfosConverter();

  @Override
  public Ticket convert(PgTicket source) {
    return new Ticket(source.getID(), ticketInfosConverter.convert(source.getTicketInfos()), null);
  }

  @Override
  public PgTicket pgConverter(Ticket target) {
    return new PgTicket(target.getID(), ticketInfosConverter.pgConverter(target.getTicketInfos()), null);
  }
}
