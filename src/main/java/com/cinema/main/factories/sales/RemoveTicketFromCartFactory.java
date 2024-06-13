package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.RemoveTicketFromCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.RemoveTicketFromCartDTO;
import com.cinema.domain.usecases.sale.RemoveTicketFromCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgTicketCartRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class RemoveTicketFromCartFactory {
  public static Controller<RemoveTicketFromCartDTO> make() {
    PgTicketCartRepository pgTicketRepository = new PgTicketCartRepository();

    RemoveTicketFromCartUseCase removeTicketFromCartUseCase = new RemoveTicketFromCartUseCase(pgTicketRepository,
        pgTicketRepository);

    RemoveTicketFromCartController removeTicketFromCartController = new RemoveTicketFromCartController(
        removeTicketFromCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(removeTicketFromCartController, pgConnection);
  }
}
