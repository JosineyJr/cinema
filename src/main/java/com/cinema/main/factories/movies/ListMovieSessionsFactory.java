package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListMovieSessionsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListMovieSessionsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieSessionRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListMovieSessionsFactory {
  /**
   * Creates a Controller object for listing movie sessions.
   *
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgMovieSessionRepository movieSessionRepository = new PgMovieSessionRepository();

    ListMovieSessionsUseCase listMovieSessionsUseCase = new ListMovieSessionsUseCase(movieSessionRepository);

    ListMovieSessionsController listMovieSessionsController = new ListMovieSessionsController(listMovieSessionsUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listMovieSessionsController, pgConnection);
  }
}
