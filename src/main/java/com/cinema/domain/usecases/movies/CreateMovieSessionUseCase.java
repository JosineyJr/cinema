package com.cinema.domain.usecases.movies;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.contracts.repositories.products.ICreateTicketRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.errors.movies.MovieSessionAlreadyScreeningInCinemaHallError;

public class CreateMovieSessionUseCase {
  private IFindMovieByIDRepository findMovieByIDRepository;
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;
  private IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
  private ICreateMovieSessionRepository createMovieSessionRepository;
  private ICreateTicketRepository createTicketRepository;

  public CreateMovieSessionUseCase(IFindMovieByIDRepository findMovieByIDRepository,
      IFindCinemaHallByIDRepository findCinemaHallByIDRepository,
      IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
      ICreateMovieSessionRepository createMovieSessionRepository,
      ICreateTicketRepository createTicketRepository) {
    this.findMovieByIDRepository = findMovieByIDRepository;
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
    this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository = findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
    this.createMovieSessionRepository = createMovieSessionRepository;
    this.createTicketRepository = createTicketRepository;
  }

  /**
   * Executes the use case to create a movie session.
   *
   * @param movieID      The ID of the movie.
   * @param cinemaHallID The ID of the cinema hall.
   * @param startTime    The start time of the movie session.
   * @param ticketPrice  The price of the movie ticket.
   * @throws MovieNotFoundError                            If the movie with the
   *                                                       given ID is not found.
   * @throws CinemaHallNotFoundError                       If the cinema hall with
   *                                                       the given ID is not
   *                                                       found.
   * @throws MovieSessionAlreadyScreeningInCinemaHallError If there is already a
   *                                                       movie session screening
   *                                                       in the cinema hall at
   *                                                       the given start time.
   */
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

    Ticket ticket = new Ticket(ticketPrice, movieSession);

    this.createTicketRepository.createTicket(ticket);

  }
}
