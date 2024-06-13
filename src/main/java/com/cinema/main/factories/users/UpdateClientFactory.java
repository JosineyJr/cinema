package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.UpdateClientController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.users.UpdateClientDTO;
import com.cinema.domain.usecases.users.UpdateClientUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateClientFactory {
  public static Controller<UpdateClientDTO> make() {
    PgPersonRepository pgPersonRepository = new PgPersonRepository();
    PgClientRepository pgClientRepository = new PgClientRepository();

    UpdateClientUseCase updateClientUseCase = new UpdateClientUseCase(pgPersonRepository, pgClientRepository);

    UpdateClientController updateClientController = new UpdateClientController(updateClientUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updateClientController, pgConnection);
  }
}
