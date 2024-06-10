package com.cinema.infra.db.postgres.repositores.products;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteTicketRepository;
import com.cinema.domain.contracts.repositories.products.IFindTicketByIDRepository;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketRepository
    extends PgRepository
    implements IDeleteTicketRepository,
    IFindTicketByIDRepository {

  public void deleteTicket(UUID ID) {
    PgTicket pgTicket = this.session.get(PgTicket.class, ID);

    PgCart cart = pgTicket.getCart();
    if (cart != null) {
      cart.getTickets().remove(pgTicket);
      pgTicket.setCart(null);
    }

    this.session.remove(pgTicket);
  }

  public Ticket findTicketByID(UUID ID) {
    PgTicket pgTicket = this.session.get(PgTicket.class, ID);

    return ConvertEntities.convertTicket(pgTicket);
  }
}
