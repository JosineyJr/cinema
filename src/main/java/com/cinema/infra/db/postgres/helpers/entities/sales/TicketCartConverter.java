package com.cinema.infra.db.postgres.helpers.entities.sales;

import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgTicketCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.TicketConverter;

public class TicketCartConverter implements IEntityConverter<PgTicketCart, TicketCart> {
  private TicketConverter ticketConverter = new TicketConverter();

  @Override
  public TicketCart convert(PgTicketCart source) {
    return new TicketCart(source.getID(), ticketConverter.convert(source.getTicket()),
        new Cart(source.getCart().getID()), source.getPrice());
  }

  @Override
  public PgTicketCart pgConverter(TicketCart target) {
    return new PgTicketCart(target.getID(), ticketConverter.pgConverter(target.getTicket()),
        new PgCart(target.getCart().getID()), target.getPrice());
  }
}
