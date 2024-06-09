package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListTicketsInfosController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListTicketsInfosUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgTicketInfosRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListTicketsFactory {
  public static Controller<Object> make() {
    PgTicketInfosRepository ticketRepository = new PgTicketInfosRepository();

    ListTicketsInfosUseCase listTicketsUseCase = new ListTicketsInfosUseCase(ticketRepository);

    ListTicketsInfosController listTicketsController = new ListTicketsInfosController(listTicketsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listTicketsController, pgConnection);
  }
}
