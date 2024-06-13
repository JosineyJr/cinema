package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.RemoveTicketCartFromCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.RemoveTicketCartFromCartDTO;
import com.cinema.domain.usecases.sale.RemoveTicketCartFromCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgTicketCartRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class RemoveTicketFromCartFactory {
  public static Controller<RemoveTicketCartFromCartDTO> make() {
    PgTicketCartRepository pgTicketRepository = new PgTicketCartRepository();

    RemoveTicketCartFromCartUseCase removeTicketFromCartUseCase = new RemoveTicketCartFromCartUseCase(
        pgTicketRepository,
        pgTicketRepository);

    RemoveTicketCartFromCartController removeTicketFromCartController = new RemoveTicketCartFromCartController(
        removeTicketFromCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(removeTicketFromCartController, pgConnection);
  }
}
