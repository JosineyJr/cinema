package com.cinema.infra.db.postgres.repositores.users;

import java.util.ArrayList;

import com.cinema.domain.contracts.repositories.users.IFindPersonByCPFRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgPersonRepository extends PgRepository implements IFindPersonByCPFRepository {

  public PgPersonRepository() {
    super();
  }

  @Override
  public Person findPersonByCPF(String cpf) {
    try {
      PgPerson pgPerson = this.session.createQuery("FROM person p WHERE p.CPF = :cpf", PgPerson.class)
          .setParameter("cpf", cpf)
          .getSingleResult();

      if (pgPerson instanceof PgClient) {
        PgClient pgClient = (PgClient) pgPerson;

        ArrayList<Genre> genres = new ArrayList<>();

        for (PgGenre pgGenre : pgClient.getMoviesPreferences()) {
          genres.add(new Genre(pgGenre.getID(), pgGenre.getName()));
        }

        return new Client(pgClient.getID(), pgClient.getFirstName(), pgClient.getLastName(), pgClient.getCPF(),
            pgClient.getPassword(), genres);
      }

      return null;
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

}
