package com.cinema.main.factories.auth;

import com.cinema.application.controllers.auth.LoginController;
import com.cinema.domain.usecases.auth.LoginUseCase;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.infra.providers.crypto.BCryptAdapter;

public class LoginFactory {
  public static LoginController makeLogin() {
    BCryptAdapter bCryptAdapter = new BCryptAdapter(12);
    PgPersonRepository findPersonByCPFRepository = new PgPersonRepository();

    LoginUseCase loginUseCase = new LoginUseCase(findPersonByCPFRepository, bCryptAdapter);

    return new LoginController(loginUseCase);
  }
}
