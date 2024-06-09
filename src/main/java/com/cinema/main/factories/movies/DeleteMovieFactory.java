package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.DeleteMovieController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.DeleteMovieDTO;
import com.cinema.domain.usecases.movies.DeleteMovieUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteMovieFactory {
  /**
   * Creates a Controller for deleting a movie.
   *
   * @return the Controller<DeleteMovieDTO> instance
   */
  public static Controller<DeleteMovieDTO> make() {
    PgMovieRepository pgMovieRepository = new PgMovieRepository();

    DeleteMovieUseCase deleteMovieUseCase = new DeleteMovieUseCase(pgMovieRepository, pgMovieRepository);

    DeleteMovieController deleteMovieController = new DeleteMovieController(deleteMovieUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteMovieController, pgConnection);
  }
}
