package com.cinema.main.factories.users;

import com.cinema.application.controllers.users.CreateClientController;
import com.cinema.domain.usecases.users.CreateClientUseCase;
import com.cinema.infra.db.crypto.BCryptAdapter;
import com.cinema.infra.db.postgres.repositores.PgClientRepository;

public class CreateClientFactory {
  public static CreateClientController makCreateClientController() {
    PgClientRepository pgClientRepository = new PgClientRepository();
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);

    CreateClientUseCase createClientUseCase = new CreateClientUseCase(pgClientRepository, pgClientRepository, bCryptAdapter);

    return new CreateClientController(createClientUseCase);
  }
}
