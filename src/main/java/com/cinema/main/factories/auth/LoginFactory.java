package com.cinema.main.factories.auth;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.auth.LoginController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.auth.LoginDTO;
import com.cinema.domain.usecases.auth.LoginUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.infra.db.postgres.repositores.users.PgEmployeeRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;
import com.cinema.main.factories.db.PgConnectionFactory;

public class LoginFactory {
  public static Controller<LoginDTO> make() {
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);
    PgClientRepository pgClientRepository = new PgClientRepository();
    PgEmployeeRepository pgEmployeeRepository = new PgEmployeeRepository();

    LoginUseCase loginUseCase = new LoginUseCase(pgClientRepository, pgEmployeeRepository, bCryptAdapter);

    LoginController loginController = new LoginController(loginUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(loginController, pgConnection);
  }
}
