package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.DeleteGenreController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.DeleteGenreDTO;
import com.cinema.domain.usecases.movies.DeleteGenreUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteGenreFactory {
  public static Controller<DeleteGenreDTO> make() {
    PgGenreRepository pgGenreRepository = new PgGenreRepository();

    DeleteGenreUseCase deleteGenreUseCase = new DeleteGenreUseCase(pgGenreRepository, pgGenreRepository);

    DeleteGenreController deleteGenreController = new DeleteGenreController(deleteGenreUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteGenreController, pgConnection);
  }
}
