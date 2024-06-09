package com.cinema.infra.db.postgres.repositores.movies;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IDeleteMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByIdRepository;
import com.cinema.domain.contracts.repositories.movies.IListMovieSessionsRepository;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgMovieSessionRepository
    extends PgRepository
    implements IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository,
    ICreateMovieSessionRepository,
    IListMovieSessionsRepository,
    IFindMovieSessionByIdRepository,
    IDeleteMovieSessionRepository {

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
        "(ms2.start_date >= :startTime AND ms2.start_date < :endTime) OR " +
        "(:startTime >= ms2.start_date AND :startTime < (ms2.start_date + (m2.duration || ' minutes')::interval)) " +
        ")";

    long movieSessionCount = session.createNativeQuery(sql, PgMovieSession.class)
        .setParameter("cinemaHallId", cinemaHallID).setParameter("startTime", sessionStartTime)
        .setParameter("endTime", sessionStartTime.plusMinutes(movieDuration)).getResultCount();

    if (movieSessionCount > 0) {
      return true;
    }

    return false;
  }

  @Override
  public UUID createMovieSession(MovieSession movieSession) {
    PgMovieSession pgMovieSession = ConvertEntities.pgConvertMovieSession(movieSession);

    this.session.persist(pgMovieSession);

    return pgMovieSession.getID();
  }

  @Override
  public List<MovieSession> listMovieSessions() {
    List<PgMovieSession> pgMovieSessions = this.session.createQuery("from movie_session", PgMovieSession.class)
        .getResultList();

    return pgMovieSessions.stream().map(pgMovieSession -> {
      return ConvertEntities.convertMovieSession(pgMovieSession);
    }).toList();
  }

  public void deleteMovieSession(UUID ID) {
    PgMovieSession pgMovieSession = this.session.find(PgMovieSession.class, ID);

    this.session.remove(pgMovieSession);
  }

  public MovieSession findMovieSessionById(UUID ID) {
    PgMovieSession pgMovieSession = this.session.get(PgMovieSession.class, ID);

    return ConvertEntities.convertMovieSession(pgMovieSession);
  }
}
