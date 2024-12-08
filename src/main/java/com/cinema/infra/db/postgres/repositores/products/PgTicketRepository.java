package com.cinema.infra.db.postgres.repositores.products;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateTicketRepository;
import com.cinema.domain.contracts.repositories.products.IFindTicketByIDRepository;
import com.cinema.domain.contracts.repositories.products.IListTicketsRepository;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketRepository extends PgRepository
    implements ICreateTicketRepository, IListTicketsRepository, IFindTicketByIDRepository {

  public PgTicketRepository() {
    super();
  }

  @Override
  public void createTicket(Ticket ticket) {
    PgTicket pgTicket = ConvertEntities.pgConvertTicket(ticket);

    this.session.persist(pgTicket);
  }

  @Override
  public List<Ticket> listTickets() {
    List<PgTicket> pgTickets = this.session.createQuery("from ticket", PgTicket.class).getResultList();

    return pgTickets.stream().map(pgTicket -> {
      return ConvertEntities.convertTicket(pgTicket);
    }).toList();
  }

  @Override
  public Ticket findByID(UUID ticketID) {
    PgTicket pgTicket = this.session.find(PgTicket.class, ticketID);

    if (pgTicket == null) {
      return null;
    }

    return ConvertEntities.convertTicket(pgTicket);
  }
}
