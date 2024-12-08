package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListMoviesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListMoviesUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListMoviesFactory {
  /**
   * Creates a Controller object for listing movies.
   *
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgMovieRepository movieRepository = new PgMovieRepository();

    ListMoviesUseCase listMoviesUseCase = new ListMoviesUseCase(movieRepository);

    ListMoviesController listMoviesController = new ListMoviesController(listMoviesUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listMoviesController, pgConnection);
  }
}
