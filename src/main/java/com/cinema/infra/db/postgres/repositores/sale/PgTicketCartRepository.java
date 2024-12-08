package com.cinema.infra.db.postgres.repositores.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.IDeleteTicketCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindTicketCartByIDRepository;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgTicketCart;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketCartRepository
    extends PgRepository
    implements IDeleteTicketCartRepository,
    IFindTicketCartByIDRepository {

  @Override
  public TicketCart findByID(UUID ID) {
    PgTicketCart pgTicket = this.session.get(PgTicketCart.class, ID);

    return ConvertEntities.convertTicketCart(pgTicket);
  }

  @Override
  public void deleteTicketCart(UUID ID) {
    PgTicketCart pgTicket = this.session.get(PgTicketCart.class, ID);

    PgCart cart = pgTicket.getCart();
    if (cart != null) {
      cart.getTickets().remove(pgTicket);
      pgTicket.setCart(null);
    }

    this.session.remove(pgTicket);
  }
}
