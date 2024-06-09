package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListTicketsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListTicketsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgTicketRepository;

public class ListTicketsFactory {
  public static Controller<Object> make() {
    PgTicketRepository ticketRepository = new PgTicketRepository();

    ListTicketsUseCase listTicketsUseCase = new ListTicketsUseCase(ticketRepository);

    ListTicketsController listTicketsController = new ListTicketsController(listTicketsUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listTicketsController, pgConnection);
  }
}
