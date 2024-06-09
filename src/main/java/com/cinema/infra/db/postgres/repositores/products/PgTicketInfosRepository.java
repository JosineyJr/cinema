package com.cinema.infra.db.postgres.repositores.products;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateTicketInfosRepository;
import com.cinema.domain.contracts.repositories.products.IFindTicketInfosByIDRepository;
import com.cinema.domain.contracts.repositories.products.IListTicketsInfosRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.entities.products.PgTicketInfos;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgTicketInfosRepository extends PgRepository
    implements ICreateTicketInfosRepository, IListTicketsInfosRepository, IFindTicketInfosByIDRepository {

  public PgTicketInfosRepository() {
    super();
  }

  @Override
  public void createTicket(TicketInfos ticketInfos) {
    UUID genreID = ticketInfos.getMovieSession().getMovie().getGenre().getID();
    String genreName = ticketInfos.getMovieSession().getMovie().getGenre().getName();

    PgGenre pgGenre = new PgGenre(genreID, genreName);

    UUID movieID = ticketInfos.getMovieSession().getMovie().getID();
    String movieTitle = ticketInfos.getMovieSession().getMovie().getTitle();
    String movieSynopsis = ticketInfos.getMovieSession().getMovie().getSynopsis();
    String movieDirector = ticketInfos.getMovieSession().getMovie().getDirector();
    int movieDuration = ticketInfos.getMovieSession().getMovie().getDuration();
    int movieMinimumAge = ticketInfos.getMovieSession().getMovie().getMinimumAge();

    PgMovie pgMovie = new PgMovie(movieID, movieTitle, movieSynopsis, movieDirector, pgGenre, movieDuration,
        movieMinimumAge);

    UUID cinemaHallID = ticketInfos.getMovieSession().getCinemaHall().getID();
    String cinemaHallName = ticketInfos.getMovieSession().getCinemaHall().getName();
    int cinemaHallCapacity = ticketInfos.getMovieSession().getCinemaHall().getCapacity();

    PgCinemaHall pgCinemaHall = new PgCinemaHall(cinemaHallID, cinemaHallCapacity, cinemaHallName);

    UUID movieSessionID = ticketInfos.getMovieSession().getID();

    PgMovieSession pgMovieSession = new PgMovieSession(movieSessionID, pgMovie, pgCinemaHall,
        ticketInfos.getMovieSession().getStartDate());

    PgTicketInfos pgTicketInfos = new PgTicketInfos(ticketInfos.getPrice(), pgMovieSession);

    this.session.persist(pgTicketInfos);
  }

  @Override
  public List<TicketInfos> listTickets() {
    List<PgTicketInfos> pgTickets = this.session.createQuery("from ticket_infos", PgTicketInfos.class).getResultList();

    return pgTickets.stream().map(pgTicketInfos -> {
      Genre genre = new Genre(pgTicketInfos.getMovieSession().getMovie().getGenre().getID(),
          pgTicketInfos.getMovieSession().getMovie().getGenre().getName());

      Movie movie = new Movie(pgTicketInfos.getMovieSession().getMovie().getID(),
          pgTicketInfos.getMovieSession().getMovie().getTitle(),
          pgTicketInfos.getMovieSession().getMovie().getSynopsis(),
          pgTicketInfos.getMovieSession().getMovie().getDirector(),
          genre,
          pgTicketInfos.getMovieSession().getMovie().getDuration(),
          pgTicketInfos.getMovieSession().getMovie().getMinimumAge());

      CinemaHall cinemaHall = new CinemaHall(pgTicketInfos.getMovieSession().getCinemaHall().getID(),
          pgTicketInfos.getMovieSession().getCinemaHall().getCapacity(),
          pgTicketInfos.getMovieSession().getCinemaHall().getName());

      MovieSession movieSession = new MovieSession(movie, cinemaHall, pgTicketInfos.getMovieSession().getStartDate());

      return new TicketInfos(pgTicketInfos.getID(), pgTicketInfos.getPrice(), movieSession);
    }).toList();
  }

  @Override
  public TicketInfos findTicketInfosByID(UUID ticketInfoID) {
    PgTicketInfos pgTicketInfos = this.session.find(PgTicketInfos.class, ticketInfoID);

    if (pgTicketInfos == null) {
      return null;
    }

    return null;

  }
}
