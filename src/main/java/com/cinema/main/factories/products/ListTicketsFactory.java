package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListTicketsInfosController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListTicketsInfosUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgTicketInfosRepository;

public class ListTicketsFactory {
  /**
   * Creates a Controller object for listing tickets.
   *
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgTicketInfosRepository ticketRepository = new PgTicketInfosRepository();

    ListTicketsInfosUseCase listTicketsUseCase = new ListTicketsInfosUseCase(ticketRepository);

    ListTicketsInfosController listTicketsController = new ListTicketsInfosController(listTicketsUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listTicketsController, pgConnection);
  }
}
