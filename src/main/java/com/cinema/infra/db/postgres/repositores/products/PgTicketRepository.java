package com.cinema.infra.db.postgres.repositores.products;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateTicketRepository;
import com.cinema.domain.contracts.repositories.products.IListTicketsRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketRepository extends PgRepository implements ICreateTicketRepository, IListTicketsRepository {

  public PgTicketRepository() {
    super();
  }

  @Override
  public void createTicket(Ticket ticket) {
    UUID genreID = ticket.getMovieSession().getMovie().getGenre().getID();
    String genreName = ticket.getMovieSession().getMovie().getGenre().getName();

    PgGenre pgGenre = new PgGenre(genreID, genreName);

    UUID movieID = ticket.getMovieSession().getMovie().getID();
    String movieTitle = ticket.getMovieSession().getMovie().getTitle();
    String movieSynopsis = ticket.getMovieSession().getMovie().getSynopsis();
    String movieDirector = ticket.getMovieSession().getMovie().getDirector();
    int movieDuration = ticket.getMovieSession().getMovie().getDuration();
    int movieMinimumAge = ticket.getMovieSession().getMovie().getMinimumAge();

    PgMovie pgMovie = new PgMovie(movieID, movieTitle, movieSynopsis, movieDirector, pgGenre, movieDuration,
        movieMinimumAge);

    UUID cinemaHallID = ticket.getMovieSession().getCinemaHall().getID();
    String cinemaHallName = ticket.getMovieSession().getCinemaHall().getName();
    int cinemaHallCapacity = ticket.getMovieSession().getCinemaHall().getCapacity();

    PgCinemaHall pgCinemaHall = new PgCinemaHall(cinemaHallID, cinemaHallCapacity, cinemaHallName);

    UUID movieSessionID = ticket.getMovieSession().getID();

    PgMovieSession pgMovieSession = new PgMovieSession(movieSessionID, pgMovie, pgCinemaHall,
        ticket.getMovieSession().getStartDate());

    PgTicket pgTicket = new PgTicket(ticket.getPrice(), pgMovieSession);

    this.session.persist(pgTicket);
  }

  @Override
  public List<Ticket> listTickets() {
    List<PgTicket> pgTickets = this.session.createQuery("from ticket", PgTicket.class).getResultList();

    return pgTickets.stream().map(pgTicket -> {
      Genre genre = new Genre(pgTicket.getMovieSession().getMovie().getGenre().getID(),
          pgTicket.getMovieSession().getMovie().getGenre().getName());

      Movie movie = new Movie(pgTicket.getMovieSession().getMovie().getID(),
          pgTicket.getMovieSession().getMovie().getTitle(),
          pgTicket.getMovieSession().getMovie().getSynopsis(), pgTicket.getMovieSession().getMovie().getDirector(),
          genre,
          pgTicket.getMovieSession().getMovie().getDuration(), pgTicket.getMovieSession().getMovie().getMinimumAge());

      CinemaHall cinemaHall = new CinemaHall(pgTicket.getMovieSession().getCinemaHall().getID(),
          pgTicket.getMovieSession().getCinemaHall().getCapacity(),
          pgTicket.getMovieSession().getCinemaHall().getName());

      MovieSession movieSession = new MovieSession(movie, cinemaHall, pgTicket.getMovieSession().getStartDate());

      return new Ticket(pgTicket.getID(), pgTicket.getPrice(), movieSession);
    }).toList();
  }
}
