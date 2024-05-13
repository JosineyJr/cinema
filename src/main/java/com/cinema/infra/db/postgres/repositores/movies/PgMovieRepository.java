package com.cinema.infra.db.postgres.repositores.movies;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieRepository;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgMovieRepository extends PgRepository implements ICreateMovieRepository {

  @Override
  public void createMovie(Movie movie) {
    PgGenre pgGenre = new PgGenre(movie.getID(), movie.getGenre().getName());

    PgMovie pgMovie = new PgMovie(movie.getTitle(), movie.getDescription(), movie.getDirector(), pgGenre,
        movie.getDuration(), movie.getMinimumAge());

    this.session.persist(pgMovie);
  }
}
