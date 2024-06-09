package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IDeleteMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IListMoviesRepository;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgMovieRepository
    extends PgRepository
    implements ICreateMovieRepository,
    IFindMovieByIDRepository,
    IListMoviesRepository,
    IDeleteMovieRepository {

  @Override
  public void createMovie(Movie movie) {

    PgMovie pgMovie = ConvertEntities.pgConvertMovie(movie);

    this.session.persist(pgMovie);
  }

  @Override
  public Movie findMovieByID(UUID ID) {
    try {

      PgMovie pgMovie = this.session.get(PgMovie.class, ID);

      return ConvertEntities.convertMovie(pgMovie);
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public List<Movie> listMovies() {
    List<PgMovie> pgMovies = this.session.createQuery("from movie", PgMovie.class).getResultList();

    return pgMovies.stream().map(pgMovie -> {
      return ConvertEntities.convertMovie(pgMovie);
    }).toList();
  }

  public void deleteMovie(UUID ID) {
    PgMovie pgMovie = this.session.get(PgMovie.class, ID);

    this.session.remove(pgMovie);
  }
}
