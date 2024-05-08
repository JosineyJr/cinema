package com.cinema.infra.db.postgres.repositores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Genre;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.entities.users.PgGenre;

import jakarta.persistence.NoResultException;

public class PgClientRepository extends PgRepository implements IFindClientByCPFRepository, ICreateClientRepository {

  public PgClientRepository() {
    super();
  }

  @Override
  public void createClient(Client client) {
    Set<PgGenre> genres = new HashSet<>();

    for (int i = 0; i < client.getMoviesPreferences().size(); i++) {
      String genreName = client.getMoviesPreferences().get(i).getName();

      PgGenreRepository pgGenreRepository = new PgGenreRepository();

      Genre genre = pgGenreRepository.findGenreByName(genreName);

      genres.add(new PgGenre(genre.getID(), genre.getName()));
    }

    PgClient pgClient = new PgClient(client.getFirstName(), client.getLastName(), client.getCPF(), client.getPassword(),
        genres);


    System.out.println(pgClient.getPassword());

    this.transaction = session.beginTransaction();
    this.session.persist(pgClient);
    this.transaction.commit();
  }

  @Override
  public Client findClientByCPF(String cpf) {
    try {
      PgClient pgClient = this.session.createQuery("FROM client c WHERE c.CPF = :cpf", PgClient.class)
          .setParameter("cpf", cpf)
          .getSingleResult();

      Set<Genre> genres = new HashSet<>();

      for (int i = 0; i < pgClient.getMoviesPreferences().size(); i++) {
        UUID ID = pgClient.getMoviesPreferences().iterator().next().getID();
        String name = pgClient.getMoviesPreferences().iterator().next().getName();
        genres.add(new Genre(ID, name));
      }

      return new Client(pgClient.getID(), pgClient.getFirstName(), pgClient.getLastName(), pgClient.getCPF(),
          pgClient.getPassword(),
          new ArrayList<Genre>(genres));
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }
}
