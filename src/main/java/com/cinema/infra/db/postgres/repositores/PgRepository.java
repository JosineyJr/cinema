package com.cinema.infra.db.postgres.repositores;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cinema.infra.db.postgres.helpers.PgConnection;


public abstract class PgRepository {
  private final PgConnection connection;
  protected Session session;
  protected Transaction transaction;

  public PgRepository() {
    this.connection = PgConnection.getInstance();
    this.session = this.connection.getSession();
    this.transaction = null;
  }
}
