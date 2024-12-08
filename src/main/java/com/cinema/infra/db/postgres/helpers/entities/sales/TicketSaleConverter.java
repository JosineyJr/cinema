package com.cinema.infra.db.postgres.helpers.entities.sales;

import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.TicketSale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgTicketSale;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.TicketConverter;

public class TicketSaleConverter implements IEntityConverter<PgTicketSale, TicketSale> {
  private TicketConverter ticketConverter = new TicketConverter();

  @Override
  public TicketSale convert(PgTicketSale source) {
    return new TicketSale(source.getID(), ticketConverter.convert(source.getTicket()),
        new Sale(source.getSale().getID()), source.getPrice());
  }

  @Override
  public PgTicketSale pgConverter(TicketSale target) {
    return new PgTicketSale(target.getID(), ticketConverter.pgConverter(target.getTicket()),
        new PgSale(target.getSale().getID()), target.getPrice());
  }

}
