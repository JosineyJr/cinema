package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.CreateCinemaHallController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.CreateCinemaHallUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgCinemaHallRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateCinemaHallFactory {
  public static Controller make() {
    PgCinemaHallRepository pgCinemaHallRepository = new PgCinemaHallRepository();

    CreateCinemaHallUseCase createCinemaHallUseCase = new CreateCinemaHallUseCase(pgCinemaHallRepository,
        pgCinemaHallRepository);

    CreateCinemaHallController createCinemaHallController = new CreateCinemaHallController(
        createCinemaHallUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController(createCinemaHallController, pgConnection);
  }
}
