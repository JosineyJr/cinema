package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListMovieSessionsController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListMovieSessionsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieSessionRepository;

public class ListMovieSessionsFactory {
  public static Controller<Object> make() {
    PgMovieSessionRepository movieSessionRepository = new PgMovieSessionRepository();

    ListMovieSessionsUseCase listMovieSessionsUseCase = new ListMovieSessionsUseCase(movieSessionRepository);

    ListMovieSessionsController listMovieSessionsController = new ListMovieSessionsController(listMovieSessionsUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listMovieSessionsController, pgConnection);
  }
}
