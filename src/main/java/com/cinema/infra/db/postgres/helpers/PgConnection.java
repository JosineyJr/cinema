package com.cinema.infra.db.postgres.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import com.cinema.application.contracts.DbTransaction;
import com.cinema.infra.db.postgres.errors.PgConnectionNotFoundError;

import jakarta.persistence.Entity;

public class PgConnection implements DbTransaction {
  private static PgConnection instance;
  private SessionFactory sessionFactory;
  private Session session;
  private Transaction transaction;

  public static synchronized PgConnection getInstance() {
    if (instance == null) {
      instance = new PgConnection();
    }
    return instance;
  }

  private PgConnection() {
    this.sessionFactory = buildSessionFactory();

    DatabaseInitializer.initGenre(getSession());
    DatabaseInitializer.initUsers(getSession());
  }

  private SessionFactory buildSessionFactory() {
    Configuration configuration = new Configuration().configure();
    Reflections reflections = new Reflections("com.cinema.infra.db.postgres.entities");

    for (Class<?> clazz : reflections.getTypesAnnotatedWith(Entity.class)) {
      configuration.addAnnotatedClass(clazz);
    }

    return configuration.buildSessionFactory();
  }

  public void connect() {
    if (this.sessionFactory.isClosed()) {
      this.sessionFactory = buildSessionFactory();
    }
    this.session = this.sessionFactory.openSession();
  }

  public void disconnect() throws PgConnectionNotFoundError {
    if (this.sessionFactory == null) {
      throw new PgConnectionNotFoundError();
    }
    this.session.close();
    this.sessionFactory.close();
  }

  public Session getSession() {
    if (this.session == null || !this.session.isOpen()) {
      this.session = this.sessionFactory.openSession();
    }
    return this.session;
  }

  @Override
  public void openTransaction() {
    this.transaction = this.session.beginTransaction();
  }

  @Override
  public void closeTransaction() {
    if (this.transaction != null && this.transaction.isActive()) {
      this.transaction.commit();
    }
  }

  @Override
  public void commit() {
    if (this.transaction != null && this.transaction.isActive()) {
      this.transaction.commit();
    }
  }

  @Override
  public void rollback() {
    if (this.transaction != null && this.transaction.isActive()) {
      this.transaction.rollback();
    }
  }
}
