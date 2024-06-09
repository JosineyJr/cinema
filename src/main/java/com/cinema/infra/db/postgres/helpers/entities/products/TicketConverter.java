package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class TicketConverter implements IEntityConverter<PgTicket, Ticket> {
  private TicketInfosConverter ticketInfosConverter = new TicketInfosConverter();

  @Override
  public Ticket convert(PgTicket source) {
    return new Ticket(source.getID(), ticketInfosConverter.convert(source.getTicketInfos()),
        new Cart(source.getCart().getID()));
  }

  @Override
  public PgTicket pgConverter(Ticket target) {
    return new PgTicket(target.getID(), ticketInfosConverter.pgConverter(target.getTicketInfos()),
        new PgCart(target.getCart().getID()));
  }
}
