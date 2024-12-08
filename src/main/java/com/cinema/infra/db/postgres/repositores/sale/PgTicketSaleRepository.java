package com.cinema.infra.db.postgres.repositores.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICreateTicketSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IFindTicketsSaleByMovieSessionID;
import com.cinema.domain.entities.sale.TicketSale;
import com.cinema.infra.db.postgres.entities.sale.PgTicketSale;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketSaleRepository extends PgRepository
    implements IFindTicketsSaleByMovieSessionID, ICreateTicketSaleRepository {

  @Override
  public List<TicketSale> findTicketsSaleByMovieSessionID(UUID movieSessionID) {
    List<PgTicketSale> pgTickets = this.session
        .createQuery("from ticket_sale where ticket.movieSession.id = :movieSessionID", PgTicketSale.class)
        .setParameter("movieSessionID", movieSessionID)
        .setCacheable(false)
        .getResultList();

    return pgTickets.stream().map(pgTicket -> {
      return ConvertEntities.convertTicketSale(pgTicket);
    }).toList();
  }

  @Override
  public UUID create(TicketSale ticketSale) {
    PgTicketSale pgTicketSale = ConvertEntities.pgConvertTicketSale(ticketSale);

    this.session.persist(pgTicketSale);

    return pgTicketSale.getID();
  }
}
