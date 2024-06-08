package com.cinema.main.factories.movies;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.movies.CreateMovieSessionController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.movies.CreateMovieSessionDTO;
import com.cinema.domain.usecases.movies.CreateMovieSessionUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.movies.PgCinemaHallRepository;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieRepository;
import com.cinema.infra.db.postgres.repositores.movies.PgMovieSessionRepository;
import com.cinema.infra.db.postgres.repositores.products.PgTicketRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateMovieSessionFactory {
  public static Controller<CreateMovieSessionDTO> make() {
    PgMovieRepository pgMovieRepository = new PgMovieRepository();

    PgCinemaHallRepository pgCinemaHallRepository = new PgCinemaHallRepository();

    PgMovieSessionRepository pgMovieSessionRepository = new PgMovieSessionRepository();

    PgTicketRepository pgTicketRepository = new PgTicketRepository();

    CreateMovieSessionUseCase createMovieSessionUseCase = new CreateMovieSessionUseCase(pgMovieRepository,
        pgCinemaHallRepository, pgMovieSessionRepository, pgMovieSessionRepository, pgTicketRepository);

    CreateMovieSessionController createMovieSessionController = new CreateMovieSessionController(
        createMovieSessionUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(createMovieSessionController, pgConnection);
  }
}
