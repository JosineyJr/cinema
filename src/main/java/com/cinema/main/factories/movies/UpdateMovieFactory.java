package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.UpdateMovieController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.UpdateMovieDTO;
import com.cinema.domain.usecases.movies.UpdateMovieUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateMovieFactory {
  public static Controller<UpdateMovieDTO> make() {
    PgMovieRepository pgMovieRepository = new PgMovieRepository();

    UpdateMovieUseCase updateMovieUseCase = new UpdateMovieUseCase(pgMovieRepository, pgMovieRepository);

    UpdateMovieController updateMovieController = new UpdateMovieController(updateMovieUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updateMovieController, pgConnection);
  }
}
