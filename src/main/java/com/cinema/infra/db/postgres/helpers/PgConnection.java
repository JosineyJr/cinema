package com.cinema.infra.db.postgres.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PgConnection {
  private static PgConnection instance;
  private static SessionFactory sessionFactory;

  public static PgConnection getInstance() {
    if (instance == null) {
      instance = new PgConnection();
    }

    return instance;
  }

  public void connect() {
    if (PgConnection.sessionFactory == null || PgConnection.sessionFactory.isClosed()) {
      PgConnection.sessionFactory = new Configuration().configure().buildSessionFactory();
    }
  }

  public void disconnect() throws PgConnectionNotFoundError {
    if(PgConnection.sessionFactory == null){
      throw new PgConnectionNotFoundError();
    }
    PgConnection.sessionFactory.close();
  }

  public Session getSession() {
    return PgConnection.sessionFactory.openSession();
  }
}
