package com.cinema.infra.db.postgres.repositores.movies;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateCinemaHallRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByNameRepository;
import com.cinema.domain.contracts.repositories.movies.IListCinemaHallRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgCinemaHallRepository extends PgRepository
    implements ICreateCinemaHallRepository, IFindCinemaHallByNameRepository, IFindCinemaHallByIDRepository,
    IListCinemaHallRepository {

  @Override
  public void createCinemaHall(CinemaHall cinemaHall) {
    PgCinemaHall pgCinemaHall = new PgCinemaHall(cinemaHall.getCapacity(), cinemaHall.getName());

    this.session.persist(pgCinemaHall);
  }

  @Override
  public CinemaHall findCinemaHallByName(String name) {
    try {
      PgCinemaHall pgCinemaHall = this.session.createQuery("where name = :name", PgCinemaHall.class)
          .setParameter("name", name).getSingleResult();

      return new CinemaHall(pgCinemaHall.getID(), pgCinemaHall.getCapacity(), pgCinemaHall.getName());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public CinemaHall findCinemaHallByID(UUID ID) {
    try {
      PgCinemaHall pgCinemaHall = this.session.find(PgCinemaHall.class, ID);

      return new CinemaHall(pgCinemaHall.getID(), pgCinemaHall.getCapacity(), pgCinemaHall.getName());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public List<CinemaHall> listCinemaHalls() {
    List<PgCinemaHall> pgCinemaHalls = this.session.createQuery("from cinema_hall", PgCinemaHall.class).getResultList();

    return pgCinemaHalls.stream().map(pgCinemaHall -> {
      return new CinemaHall(pgCinemaHall.getID(), pgCinemaHall.getCapacity(), pgCinemaHall.getName());
    }).toList();
  }
}
