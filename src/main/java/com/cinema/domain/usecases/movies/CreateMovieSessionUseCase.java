package com.cinema.domain.usecases.movies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.contracts.repositories.movies.IListMoviesRepository;
import com.cinema.domain.contracts.repositories.products.ICreateTicketRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.errors.movies.MovieSessionAlreadyScreeningInCinemaHallError;
import com.cinema.domain.usecases.movies.comparators.MovieComparator;

public class CreateMovieSessionUseCase {
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;
  private IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
  private ICreateMovieSessionRepository createMovieSessionRepository;
  private ICreateTicketRepository createTicketRepository;
  private IListMoviesRepository listMoviesRepository;

  public CreateMovieSessionUseCase(IFindCinemaHallByIDRepository findCinemaHallByIDRepository,
      IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
      ICreateMovieSessionRepository createMovieSessionRepository, ICreateTicketRepository createTicketRepository,
      IListMoviesRepository listMoviesRepository) {
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
    this.findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository = findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
    this.createMovieSessionRepository = createMovieSessionRepository;
    this.createTicketRepository = createTicketRepository;
    this.listMoviesRepository = listMoviesRepository;
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

    List<Movie> movies = this.listMoviesRepository.listMovies();

    Movie movieToCompare = new Movie(movieID);

    Movie movie = this.findMovie(movies, movieToCompare);

    this.findMovieBinarySearch(movies, movieToCompare);

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

  private Movie findMovie(List<Movie> movies, Movie movieToFind) {
    long startTime = System.nanoTime();

    Iterator<Movie> iterator = movies.iterator();

    while (iterator.hasNext()) {
      Movie movie = iterator.next();

      MovieComparator movieComparator = new MovieComparator();

      if (movieComparator.compare(movie, movieToFind) == 0) {
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Linear search execution time: " + executionTime + " nanoseconds");
        return movie;
      }
    }

    long endTime = System.nanoTime();
    long executionTime = endTime - startTime;
    System.out.println("Linear searcfindMovieBinarySearchh execution time: " + executionTime + "nanoseconds");

    return null;
  }

  private Movie findMovieBinarySearch(List<Movie> movies, Movie movieToFind) {
    long startTime = System.nanoTime();

    List<Movie> sortedMovies = new ArrayList<>(movies);

    MovieComparator movieComparator = new MovieComparator();
    Collections.sort(sortedMovies, movieComparator);

    int index = Collections.binarySearch(sortedMovies, movieToFind, movieComparator);

    if (index >= 0) {
      long endTime = System.nanoTime();
      long executionTime = endTime - startTime;
      System.out.println("Binary search execution time: " + executionTime + "nanoseconds");
      return sortedMovies.get(index);
    }

    long endTime = System.nanoTime();
    long executionTime = endTime - startTime;
    System.out.println("Binary search execution time: " + executionTime + "nanoseconds");

    return null;
  }
}
