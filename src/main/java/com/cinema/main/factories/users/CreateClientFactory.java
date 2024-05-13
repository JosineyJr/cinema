package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.CreateClientController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.users.CreateClientUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateClientFactory {
  public static Controller make() {
    PgClientRepository pgClientRepository = new PgClientRepository();
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);

    CreateClientUseCase createClientUseCase = new CreateClientUseCase(pgClientRepository, pgClientRepository,
        bCryptAdapter);

    CreateClientController createClientController = new CreateClientController(createClientUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController(createClientController, pgConnection);
  }
}
