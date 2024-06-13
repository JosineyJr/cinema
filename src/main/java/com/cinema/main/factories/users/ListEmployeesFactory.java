package com.cinema.main.factories.users;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.users.ListEmployeesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.users.ListEmployeesUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.users.PgEmployeeRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListEmployeesFactory {
  public static Controller<Object> make() {
    PgEmployeeRepository pgEmployeeRepository = new PgEmployeeRepository();

    ListEmployeesUseCase listEmployeesUseCase = new ListEmployeesUseCase(pgEmployeeRepository);

    ListEmployeesController listEmployeesController = new ListEmployeesController(listEmployeesUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listEmployeesController, pgConnection);
  }
}
