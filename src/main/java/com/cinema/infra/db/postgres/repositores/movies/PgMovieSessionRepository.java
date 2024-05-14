package com.cinema.infra.db.postgres.repositores.movies;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgMovieSessionRepository extends PgRepository
    implements IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
    ICreateMovieSessionRepository {

  public PgMovieSessionRepository() {
    super();
  }

  @Override
  public boolean findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(UUID cinemaHallID,
      LocalDateTime sessionStartTime, int movieDuration) {
    String sql = "SELECT ms.* " +
        "FROM movie_session ms " +
        "JOIN movie_session ms2 ON ms.cinema_hall_id = ms2.cinema_hall_id " +
        "JOIN movie m2 ON ms2.movie_id = m2.ID " +
        "WHERE ms.cinema_hall_id = :cinemaHallId " +
        "AND ( " +
        "(ms2.start_time >= :startTime AND ms2.start_time < :endTime) OR " +
        "(:startTime >= ms2.start_time AND :startTime < (ms2.start_time + (m2.duration || ' minutes')::interval)) " +
        ");";
    ;

    long movieSessionCount = session.createNativeQuery(sql, PgMovieSession.class)
        .setParameter("cinemaHallId", cinemaHallID).setParameter("startTime", sessionStartTime)
        .setParameter("endTime", sessionStartTime.plusMinutes(movieDuration)).getResultCount();

    if (movieSessionCount > 0) {
      return true;
    }

    return false;
  }

  @Override
  public void createMovieSession(MovieSession movieSession) {
    PgGenre pgGenre = new PgGenre(movieSession.getMovie().getGenre().getID(),
        movieSession.getMovie().getGenre().getName());

    PgMovie pgMovie = new PgMovie(movieSession.getMovie().getID(), movieSession.getMovie().getTitle(),
        movieSession.getMovie().getSynopsis(), movieSession.getMovie().getDirector(), pgGenre,
        movieSession.getMovie().getDuration(), movieSession.getMovie().getMinimumAge());

    PgCinemaHall pgCinemaHall = new PgCinemaHall(movieSession.getCinemaHall().getID(),
        movieSession.getCinemaHall().getCapacity(), movieSession.getCinemaHall().getName());

    PgMovieSession pgMovieSession = new PgMovieSession(pgMovie, pgCinemaHall,
        movieSession.getStartTime());

    this.session.persist(pgMovieSession);
  }
}
