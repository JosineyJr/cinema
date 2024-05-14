package com.cinema.infra.db.postgres.repositores.movies;

import com.cinema.domain.contracts.repositories.movies.ICreateCinemaHallRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByNameRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgCinemaHallRepository extends PgRepository
    implements ICreateCinemaHallRepository, IFindCinemaHallByNameRepository {

  @Override
  public void createCinemaHall(CinemaHall cinemaHall) {
    PgCinemaHall pgCinemaHall = new PgCinemaHall(cinemaHall.getNumberOfChairs(), cinemaHall.getName());

    this.session.persist(pgCinemaHall);
  }

  @Override
  public CinemaHall findCinemaHallByName(String name) {
    try {
      PgCinemaHall pgCinemaHall = this.session.createQuery("where name = :name", PgCinemaHall.class)
          .setParameter("name", name).getSingleResult();

      return new CinemaHall(pgCinemaHall.getID(), pgCinemaHall.getNumberOfChairs(), pgCinemaHall.getName());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }
}
