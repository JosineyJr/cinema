package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IListMoviesRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgMovieRepository extends PgRepository
    implements ICreateMovieRepository, IFindMovieByIDRepository, IListMoviesRepository {

  @Override
  public void createMovie(Movie movie) {
    PgGenre pgGenre = new PgGenre(movie.getGenre().getID(), movie.getGenre().getName());

    PgMovie pgMovie = new PgMovie(movie.getTitle(), movie.getSynopsis(), movie.getDirector(), pgGenre,
        movie.getDuration(), movie.getMinimumAge());

    this.session.persist(pgMovie);
  }

  @Override
  public Movie findMovieByID(UUID ID) {
    try {

      PgMovie pgMovie = this.session.get(PgMovie.class, ID);

      Genre genre = new Genre(pgMovie.getGenre().getID(), pgMovie.getGenre().getName());

      return new Movie(pgMovie.getID(), pgMovie.getTitle(), pgMovie.getSynopsis(), pgMovie.getDirector(), genre,
          pgMovie.getDuration(), pgMovie.getMinimumAge());
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
      Genre genre = new Genre(pgMovie.getGenre().getID(), pgMovie.getGenre().getName());

      return new Movie(pgMovie.getID(), pgMovie.getTitle(), pgMovie.getSynopsis(), pgMovie.getDirector(), genre,
          pgMovie.getDuration(), pgMovie.getMinimumAge());
    }).toList();
  }
}
