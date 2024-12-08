package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.AddTicketToCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.AddTicketToCartDTO;
import com.cinema.domain.usecases.sale.AddTicketToCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgTicketRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgCartRepository;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class AddTicketToCartFactory {
  public static Controller<AddTicketToCartDTO> make() {
    PgTicketRepository pgTicketRepository = new PgTicketRepository();
    PgCartRepository pgCartRepository = new PgCartRepository();
    PgPersonRepository pgPersonRepository = new PgPersonRepository();

    AddTicketToCartUseCase addTicketToCartUseCase = new AddTicketToCartUseCase(pgTicketRepository,
        pgCartRepository, pgPersonRepository, pgCartRepository, pgCartRepository);

    AddTicketToCartController addTicketToCartController = new AddTicketToCartController(addTicketToCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(addTicketToCartController, pgConnection);
  }
}
