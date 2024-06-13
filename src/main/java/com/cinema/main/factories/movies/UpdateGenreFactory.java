package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.UpdateGenreController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.UpdateGenreDTO;
import com.cinema.domain.usecases.movies.UpdateGenreUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateGenreFactory {
  /**
   * Creates a Controller instance for updating a genre.
   *
   * @return The created Controller instance.
   */
  public static Controller<UpdateGenreDTO> make() {
    PgGenreRepository pgGenreRepository = new PgGenreRepository();

    UpdateGenreUseCase updateGenreUseCase = new UpdateGenreUseCase(pgGenreRepository);

    UpdateGenreController updateGenreController = new UpdateGenreController(updateGenreUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updateGenreController, pgConnection);
  }
}
