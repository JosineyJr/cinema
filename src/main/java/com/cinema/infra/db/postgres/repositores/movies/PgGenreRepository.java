package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateGenreRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByNameRepository;
import com.cinema.domain.contracts.repositories.movies.IListGenresRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgGenreRepository extends PgRepository
    implements ICreateGenreRepository, IFindGenreByNameRepository, IFindGenreByIDRepository, IListGenresRepository {

  @Override
  public Genre findGenreByName(String name) {
    try {
      PgGenre pgGenre = this.session.createQuery("where name = :name", PgGenre.class).setParameter("name", name)
          .getSingleResult();

      return new Genre(pgGenre.getID(), pgGenre.getName());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void createGenre(Genre genre) {
    PgGenre pgGenre = new PgGenre(genre.getName());

    this.session.persist(pgGenre);
  }

  @Override
  public Genre findGenreByID(UUID ID) {
    try {
      PgGenre pgGenre = this.session.find(PgGenre.class, ID);

      return new Genre(pgGenre.getID(), pgGenre.getName());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public List<Genre> listGenres() {
    List<PgGenre> pgGenres = this.session.createQuery("from genre", PgGenre.class).getResultList();

    return pgGenres.stream().map(pgGenre -> new Genre(pgGenre.getID(), pgGenre.getName())).toList();
  }
}
