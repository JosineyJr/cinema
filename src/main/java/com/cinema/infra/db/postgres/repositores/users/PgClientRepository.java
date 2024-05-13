package com.cinema.infra.db.postgres.repositores.users;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.users.Client;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.repositores.PgRepository;
import com.cinema.infra.db.postgres.repositores.movies.PgGenreRepository;

import jakarta.persistence.NoResultException;

public class PgClientRepository extends PgRepository implements ICreateClientRepository, IFindClientByCPFRepository {

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

    this.session.persist(pgClient);
  }

  @Override
  public Client findClientByCPF(String cpf) {
    try {
      PgClient pgClient = this.session.createQuery("FROM client c WHERE c.CPF = :cpf", PgClient.class)
          .setParameter("cpf", cpf)
          .getSingleResult();

      ArrayList<Genre> genres = new ArrayList<>();

      for (PgGenre pgGenre : pgClient.getMoviesPreferences()) {
        genres.add(new Genre(pgGenre.getID(), pgGenre.getName()));
      }

      return new Client(pgClient.getID(), pgClient.getFirstName(), pgClient.getLastName(), pgClient.getCPF(),
          pgClient.getPassword(), genres);
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }
}
