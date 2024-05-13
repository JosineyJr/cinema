package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.CreateEmployeeController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.users.CreateEmployeeUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgAdminRepository;
import com.cinema.infra.db.postgres.repositores.users.PgEmployeeRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateEmployeeFactory {
  public static Controller make() {
    PgEmployeeRepository pgEmployeeRepository = new PgEmployeeRepository();
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);
    PgAdminRepository pgAdminRepository = new PgAdminRepository();

    CreateEmployeeUseCase createEmployeeUseCase = new CreateEmployeeUseCase(pgEmployeeRepository, bCryptAdapter,
        pgEmployeeRepository, pgAdminRepository);

    CreateEmployeeController createEmployeeController = new CreateEmployeeController(createEmployeeUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController(createEmployeeController, pgConnection);
  }
}
