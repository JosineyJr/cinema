package com.cinema.main.factories.users;

import com.cinema.application.controllers.users.CreateClientController;
import com.cinema.domain.usecases.users.CreateClientUseCase;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;

public class CreateClientFactory {
  public static CreateClientController makeCreateClientController() {
    PgClientRepository pgClientRepository = new PgClientRepository();
    PgPersonRepository pgPersonRepository = new PgPersonRepository();
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);

    CreateClientUseCase createClientUseCase = new CreateClientUseCase(pgClientRepository, pgPersonRepository, bCryptAdapter);

    return new CreateClientController(createClientUseCase);
  }
}
