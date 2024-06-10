package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.ListPersonsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.users.ListPersonsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListPersonsFactory {
  public static Controller<Object> make() {
    PgPersonRepository pgPersonRepository = new PgPersonRepository();

    ListPersonsUseCase listPersonsUseCase = new ListPersonsUseCase(pgPersonRepository);

    ListPersonsController listPersonsController = new ListPersonsController(listPersonsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listPersonsController, pgConnection);
  }
}
