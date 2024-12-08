package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.CreateGenreController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.CreateGenreDTO;
import com.cinema.domain.usecases.movies.CreateGenreUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateGenreFactory {
  /**
   * Creates a Controller instance for creating a genre.
   *
   * @return the Controller instance for creating a genre
   */
  public static Controller<CreateGenreDTO> make() {
    PgGenreRepository genreRepository = new PgGenreRepository();

    CreateGenreUseCase createGenreUseCase = new CreateGenreUseCase(genreRepository, genreRepository);

    CreateGenreController genreController = new CreateGenreController(createGenreUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(genreController, pgConnection);
  }
}
