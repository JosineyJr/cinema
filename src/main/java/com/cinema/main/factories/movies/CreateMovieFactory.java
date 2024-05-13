package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.CreateMovieController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.CreateMovieUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateMovieFactory {
  public static Controller make() {
    PgMovieRepository pgMovieRepository = new PgMovieRepository();
    PgGenreRepository pgGenreRepository = new PgGenreRepository();

    CreateMovieUseCase createMovieUseCase = new CreateMovieUseCase(pgMovieRepository, pgGenreRepository);

    CreateMovieController createMovieController = new CreateMovieController(createMovieUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController(createMovieController, pgConnection);
  }
}
