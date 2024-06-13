package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IDeleteMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IListMoviesRepository;
import com.cinema.domain.contracts.repositories.movies.IUpdateMovieRepository;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

/**
 * This class represents a PostgreSQL implementation of the movie repository.
 * It provides methods to create, find, list, and delete movies in the database.
 */
public class PgMovieRepository
    extends PgRepository
    implements ICreateMovieRepository,
    IFindMovieByIDRepository,
    IListMoviesRepository,
    IDeleteMovieRepository,
    IUpdateMovieRepository {

  /**
   * Creates a new movie in the database.
   *
   * @param movie The movie to be created.
   */
  @Override
  public void createMovie(Movie movie) {

    PgMovie pgMovie = ConvertEntities.pgConvertMovie(movie);

    this.session.persist(pgMovie);
  }

  /**
   * Finds a movie in the database by its ID.
   *
   * @param ID The ID of the movie to find.
   * @return The found movie, or null if not found.
   */
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

  /**
   * Lists all movies in the database.
   *
   * @return A list of all movies.
   */
  @Override
  public List<Movie> listMovies() {
    List<PgMovie> pgMovies = this.session.createQuery("from movie", PgMovie.class).getResultList();

    return pgMovies.stream().map(pgMovie -> {
      return ConvertEntities.convertMovie(pgMovie);
    }).toList();
  }

  /**
   * Deletes a movie from the database by its ID.
   *
   * @param ID The ID of the movie to delete.
   */
  public void deleteMovie(UUID ID) {
    PgMovie pgMovie = this.session.get(PgMovie.class, ID);

    this.session.remove(pgMovie);
  }

  public void updateMovie(Movie movie) {
    PgMovie pgMovie = ConvertEntities.pgConvertMovie(movie);

    this.session.merge(pgMovie);
  }
}
