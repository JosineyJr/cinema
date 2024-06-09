package com.cinema.infra.db.postgres.repositores;

import org.hibernate.Session;

import com.cinema.infra.db.postgres.helpers.PgConnection;

public abstract class PgRepository {
  protected Session session;

  public PgRepository() {
    this.session = PgConnection.getInstance().getSession();
  }
}
