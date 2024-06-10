package com.cinema.infra.db.postgres.repositores.products;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateTicketInfosRepository;
import com.cinema.domain.contracts.repositories.products.IFindTicketInfosByIDRepository;
import com.cinema.domain.contracts.repositories.products.IListTicketsInfosRepository;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.infra.db.postgres.entities.products.PgTicketInfos;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketInfosRepository extends PgRepository
    implements ICreateTicketInfosRepository, IListTicketsInfosRepository, IFindTicketInfosByIDRepository {

  public PgTicketInfosRepository() {
    super();
  }

  @Override
  public void createTicket(TicketInfos ticketInfos) {
    PgTicketInfos pgTicketInfos = ConvertEntities.pgConvertTicketInfos(ticketInfos);

    this.session.persist(pgTicketInfos);
  }

  @Override
  public List<TicketInfos> listTickets() {
    List<PgTicketInfos> pgTickets = this.session.createQuery("from ticket_infos", PgTicketInfos.class).getResultList();

    return pgTickets.stream().map(pgTicketInfos -> {
      return ConvertEntities.convertTicketInfos(pgTicketInfos);
    }).toList();
  }

  @Override
  public TicketInfos findTicketInfosByID(UUID ticketInfoID) {
    PgTicketInfos pgTicketInfos = this.session.find(PgTicketInfos.class, ticketInfoID);

    if (pgTicketInfos == null) {
      return null;
    }

    return ConvertEntities.convertTicketInfos(pgTicketInfos);
  }
}
