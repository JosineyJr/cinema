package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListMoviesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListMoviesUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;

public class ListMoviesFactory {
  public static Controller<Object> make() {
    PgMovieRepository movieRepository = new PgMovieRepository();

    ListMoviesUseCase listMoviesUseCase = new ListMoviesUseCase(movieRepository);

    ListMoviesController listMoviesController = new ListMoviesController(listMoviesUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listMoviesController, pgConnection);
  }
}
