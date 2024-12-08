package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListTicketsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListTicketsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgTicketRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListTicketsFactory {
  /**
   * Creates a Controller object for listing tickets.
   *
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgTicketRepository ticketRepository = new PgTicketRepository();

    ListTicketsUseCase listTicketsUseCase = new ListTicketsUseCase(ticketRepository);

    ListTicketsController listTicketsController = new ListTicketsController(listTicketsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listTicketsController, pgConnection);
  }
}
