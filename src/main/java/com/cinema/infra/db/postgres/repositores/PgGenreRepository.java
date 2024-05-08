package com.cinema.infra.db.postgres.repositores;

import com.cinema.domain.contracts.repositories.users.ICreateGenreRepository;
import com.cinema.domain.contracts.repositories.users.IFindGenreByNameRepository;
import com.cinema.domain.entities.users.Genre;
import com.cinema.infra.db.postgres.entities.users.PgGenre;

import jakarta.persistence.NoResultException;

public class PgGenreRepository extends PgRepository implements ICreateGenreRepository, IFindGenreByNameRepository {

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
  public void createGenre(String name) {
    PgGenre pgGenre = new PgGenre(name);

    this.transaction = session.beginTransaction();
    this.session.persist(pgGenre);
    this.transaction.commit();
  }
}
