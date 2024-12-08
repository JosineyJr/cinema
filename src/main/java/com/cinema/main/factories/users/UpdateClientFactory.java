package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.UpdateClientController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.users.UpdateClientDTO;
import com.cinema.domain.usecases.users.UpdatePersonUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateClientFactory {
  public static Controller<UpdateClientDTO> make() {
    PgPersonRepository pgPersonRepository = new PgPersonRepository();

    UpdatePersonUseCase updatePersonUseCase = new UpdatePersonUseCase(pgPersonRepository, pgPersonRepository);

    UpdateClientController updatePersonController = new UpdateClientController(updatePersonUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updatePersonController, pgConnection);
  }
}
