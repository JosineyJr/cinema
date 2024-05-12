package com.cinema.main.factories.auth;

import com.cinema.application.controllers.auth.LoginController;
import com.cinema.domain.usecases.auth.LoginUseCase;
import com.cinema.infra.db.postgres.repositores.users.PgClientRepository;
import com.cinema.infra.db.postgres.repositores.users.PgEmployeeRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;

public class LoginFactory {
  public static LoginController makeLogin() {
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);
    PgClientRepository pgClientRepository = new PgClientRepository();
    PgEmployeeRepository pgEmployeeRepository = new PgEmployeeRepository();

    LoginUseCase loginUseCase = new LoginUseCase(pgClientRepository, pgEmployeeRepository, bCryptAdapter);

    return new LoginController(loginUseCase);
  }
}
