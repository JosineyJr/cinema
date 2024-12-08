package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.UpdateCinemaHallController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.UpdateCinemaHallDTO;
import com.cinema.domain.usecases.movies.UpdateCinemaHallUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgCinemaHallRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateCinemaHallFactory {
  /**
   * Creates a Controller instance for updating a cinema hall.
   * 
   * @return The created Controller instance.
   */
  public static Controller<UpdateCinemaHallDTO> make() {
    PgCinemaHallRepository pgCinemaHallRepository = new PgCinemaHallRepository();

    UpdateCinemaHallUseCase updateCinemaHallUseCase = new UpdateCinemaHallUseCase(pgCinemaHallRepository,
        pgCinemaHallRepository);

    UpdateCinemaHallController updateCinemaHallController = new UpdateCinemaHallController(updateCinemaHallUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updateCinemaHallController, pgConnection);
  }
}
