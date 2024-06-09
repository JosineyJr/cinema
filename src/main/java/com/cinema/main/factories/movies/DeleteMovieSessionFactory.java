package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.DeleteMovieSessionController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.DeleteMovieSessionDTO;
import com.cinema.domain.usecases.movies.DeleteMovieSessionUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieSessionRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteMovieSessionFactory {
  /**
   * Creates a Controller for deleting a movie session.
   *
   * @return the Controller<DeleteMovieSessionDTO> instance
   */
  public static Controller<DeleteMovieSessionDTO> make() {
    PgMovieSessionRepository pgMovieSessionRepository = new PgMovieSessionRepository();

    DeleteMovieSessionUseCase deleteMovieSessionUseCase = new DeleteMovieSessionUseCase(pgMovieSessionRepository,
        pgMovieSessionRepository);

    DeleteMovieSessionController deleteMovieSessionController = new DeleteMovieSessionController(
        deleteMovieSessionUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteMovieSessionController, pgConnection);
  }
}
