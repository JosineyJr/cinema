package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.errors.movies.MovieSessionAlreadyScreeningInCinemaHallError;

import java.time.LocalDateTime;

public class CreateMovieSessionUseCase {
  private IFindMovieByIDRepository findMovieByIDRepository;
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;
  private IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
  private ICreateMovieSessionRepository createMovieSessionRepository;

  public CreateMovieSessionUseCase(IFindMovieByIDRepository findMovieByIDRepository,
      IFindCinemaHallByIDRepository findCinemaHallByIDRepository,
      IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
      ICreateMovieSessionRepository createMovieSessionRepository) {
    this.findMovieByIDRepository = findMovieByIDRepository;
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
    this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository = findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
    this.createMovieSessionRepository = createMovieSessionRepository;
  }

  public void execute(UUID movieID, UUID cinemaHallID, LocalDateTime startTime)
      throws MovieNotFoundError, CinemaHallNotFoundError, MovieSessionAlreadyScreeningInCinemaHallError {
    Movie movie = this.findMovieByIDRepository.findMovieByID(movieID);

    if (movie == null) {
      throw new MovieNotFoundError();
    }

    CinemaHall cinemaHall = this.findCinemaHallByIDRepository.findCinemaHallByID(cinemaHallID);

    if (cinemaHall == null) {
      throw new CinemaHallNotFoundError();
    }

    int breakTimeBetweenSessions = MovieSession.BREAK_TIME_BETWEEN_SESSIONS;

    boolean movieSessionIsExists = this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository
        .findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(cinemaHallID, startTime,
            movie.getDuration() + breakTimeBetweenSessions);

    if (movieSessionIsExists) {
      throw new MovieSessionAlreadyScreeningInCinemaHallError();
    }

    MovieSession movieSession = new MovieSession(movie, cinemaHall, startTime);

    this.createMovieSessionRepository.createMovieSession(movieSession);
  }
}
