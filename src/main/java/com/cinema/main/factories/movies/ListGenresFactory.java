package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.ListGenresController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.movies.ListGenresUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;

public class ListGenresFactory {
  public static Controller<Object> make() {
    PgGenreRepository genreRepository = new PgGenreRepository();

    ListGenresUseCase listGenresUseCase = new ListGenresUseCase(genreRepository);

    ListGenresController listGenresController = new ListGenresController(listGenresUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listGenresController, pgConnection);
  }
}
