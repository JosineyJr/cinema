package com.cinema.infra.db.postgres.repositores.users;

import java.util.List;
import java.util.stream.Collectors;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.contracts.repositories.users.IListClientsRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgClientRepository
    extends PgRepository
    implements ICreateClientRepository,
    IFindClientByCPFRepository,
    IListClientsRepository {

  public PgClientRepository() {
    super();
  }

  @Override
  public void createClient(Client client) {
    PgClient pgClient = ConvertEntities.pgConvertClient(client);

    System.out.println("CPF " + pgClient.getCPF());
    System.out.println("Genreos " + pgClient.getMoviesPreferences().get(0).getID());

    this.session.persist(pgClient);
  }

  @Override
  public Client findClientByCPF(String cpf) {
    try {
      PgClient pgClient = this.session.createQuery("FROM client c WHERE c.CPF = :cpf", PgClient.class)
          .setParameter("cpf", cpf)
          .getSingleResult();

      return ConvertEntities.convertClient(pgClient);
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Client> listClients() {
    List<PgClient> pgClients = this.session.createQuery("FROM client", PgClient.class).getResultList();

    return pgClients.stream()
        .map(pgClient -> ConvertEntities.convertClient(pgClient))
        .collect(Collectors.toList());
  }
}
