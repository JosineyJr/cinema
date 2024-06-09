package com.cinema.domain.usecases.movies;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.contracts.repositories.products.ICreateTicketInfosRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.errors.movies.MovieSessionAlreadyScreeningInCinemaHallError;

public class CreateMovieSessionUseCase {
  private IFindMovieByIDRepository findMovieByIDRepository;
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;
  private IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
  private ICreateMovieSessionRepository createMovieSessionRepository;
  private ICreateTicketInfosRepository createTicketInfosRepository;

  public CreateMovieSessionUseCase(IFindMovieByIDRepository findMovieByIDRepository,
      IFindCinemaHallByIDRepository findCinemaHallByIDRepository,
      IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
      ICreateMovieSessionRepository createMovieSessionRepository,
      ICreateTicketInfosRepository createTicketInfosRepository) {
    this.findMovieByIDRepository = findMovieByIDRepository;
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
    this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository = findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
    this.createMovieSessionRepository = createMovieSessionRepository;
    this.createTicketInfosRepository = createTicketInfosRepository;
  }

  public void execute(UUID movieID, UUID cinemaHallID, LocalDateTime startTime, double ticketPrice)
      throws MovieNotFoundError, CinemaHallNotFoundError, MovieSessionAlreadyScreeningInCinemaHallError {

    Movie movie = this.findMovieByIDRepository.findMovieByID(movieID);

    if (movie == null) {
      throw new MovieNotFoundError();
    }

    CinemaHall cinemaHall = this.findCinemaHallByIDRepository.findCinemaHallByID(cinemaHallID);

    if (cinemaHall == null) {
      throw new CinemaHallNotFoundError();
    }

    boolean movieSessionIsExists = this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository
        .findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(cinemaHallID, startTime,
            movie.getDuration());

    if (movieSessionIsExists) {
      throw new MovieSessionAlreadyScreeningInCinemaHallError();
    }

    MovieSession movieSession = new MovieSession(movie, cinemaHall, startTime);

    UUID movieSessionID = this.createMovieSessionRepository.createMovieSession(movieSession);

    movieSession.setID(movieSessionID);

    TicketInfos ticket = new TicketInfos(ticketPrice, movieSession);

    this.createTicketInfosRepository.createTicket(ticket);

  }
}
