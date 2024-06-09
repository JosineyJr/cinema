package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.DeleteCinemaHallController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.DeleteCinemaHallDTO;
import com.cinema.domain.usecases.movies.DeleteCinemaHallUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgCinemaHallRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteCinemaHallFactory {
  /**
   * Creates a Controller instance for deleting a cinema hall.
   * 
   * @return The Controller instance for deleting a cinema hall.
   */
  public static Controller<DeleteCinemaHallDTO> make() {
    PgCinemaHallRepository cinemaHallRepository = new PgCinemaHallRepository();

    DeleteCinemaHallUseCase deleteCinemaHallUseCase = new DeleteCinemaHallUseCase(cinemaHallRepository,
        cinemaHallRepository);

    DeleteCinemaHallController deleteCinemaHallController = new DeleteCinemaHallController(deleteCinemaHallUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteCinemaHallController, pgConnection);
  }
}
