package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListCinemaHallsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListCinemaHallsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgCinemaHallRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListCinemaHallsFactory {
  public static Controller<Object> make() {
    PgCinemaHallRepository cinemaHallRepository = new PgCinemaHallRepository();

    ListCinemaHallsUseCase listCinemaHallsUseCase = new ListCinemaHallsUseCase(cinemaHallRepository);

    ListCinemaHallsController listCinemaHallsController = new ListCinemaHallsController(listCinemaHallsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listCinemaHallsController, pgConnection);
  }
}
