package com.cinema.infra.db.postgres.repositores;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.helpers.PgConnection;

public class PgClientRepository implements ICreateClientRepository {
  private final PgConnection connection;

  public PgClientRepository() {
    this.connection = PgConnection.getInstance();
  }

  @Override
  public void createClient(Client client) {
    PgClient pgClient = new PgClient(client.getFirstName(), client.getLastName(), client.getCPF(),
        client.getMoviesPreferences());

    Session session = this.connection.getSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.persist(pgClient);
      transaction.commit();
    } catch (Exception e) {
      // if (transaction != null) {
        // transaction.rollback();
      // }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
