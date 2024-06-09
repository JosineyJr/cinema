package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateGenreRepository;
import com.cinema.domain.contracts.repositories.movies.IDeleteGenreRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByNameRepository;
import com.cinema.domain.contracts.repositories.movies.IListGenresRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

/**
 * The PostgreSQL implementation of the Genre repository.
 * This class provides methods to interact with the database for Genre entities.
 */
public class PgGenreRepository
    extends PgRepository
    implements ICreateGenreRepository,
    IFindGenreByNameRepository,
    IFindGenreByIDRepository,
    IListGenresRepository,
    IDeleteGenreRepository {

  /**
   * Finds a genre by its name.
   *
   * @param name The name of the genre to find.
   * @return The Genre object if found, or null if not found.
   */
  @Override
  public Genre findGenreByName(String name) {
    try {
      PgGenre pgGenre = this.session.createQuery("where name = :name", PgGenre.class).setParameter("name", name)
          .getSingleResult();

      return ConvertEntities.convertGenre(pgGenre);
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Creates a new genre.
   *
   * @param genre The Genre object to create.
   */
  @Override
  public void createGenre(Genre genre) {
    PgGenre pgGenre = new PgGenre(genre.getName());

    this.session.persist(pgGenre);
  }

  /**
   * Finds a genre by its ID.
   *
   * @param ID The ID of the genre to find.
   * @return The Genre object if found, or null if not found.
   */
  @Override
  public Genre findGenreByID(UUID ID) {
    try {
      PgGenre pgGenre = this.session.find(PgGenre.class, ID);

      return ConvertEntities.convertGenre(pgGenre);
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Lists all genres.
   *
   * @return A list of Genre objects.
   */
  @Override
  public List<Genre> listGenres() {
    List<PgGenre> pgGenres = this.session.createQuery("from genre", PgGenre.class).getResultList();

    return pgGenres.stream().map(pgGenre -> ConvertEntities.convertGenre(pgGenre)).toList();
  }

  /**
   * Deletes a genre by its ID.
   *
   * @param ID The ID of the genre to delete.
   */
  public void deleteGenre(UUID ID) {
    PgGenre pgGenre = this.session.get(PgGenre.class, ID);

    this.session.remove(pgGenre);
  }
}
