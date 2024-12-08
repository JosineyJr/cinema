package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.ListClientsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.users.ListClientsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListClientsFactory {
  public static Controller<Object> make() {
    PgClientRepository pgClientRepository = new PgClientRepository();

    ListClientsUseCase listClientsUseCase = new ListClientsUseCase(pgClientRepository);

    ListClientsController listClientsController = new ListClientsController(listClientsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listClientsController, pgConnection);
  }
}
