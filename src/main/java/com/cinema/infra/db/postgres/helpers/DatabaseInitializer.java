package com.cinema.infra.db.postgres.helpers;

import org.hibernate.Session;

public class DatabaseInitializer {
  public static void iniGenre(Session session) {
    session.beginTransaction();
    session.createMutationQuery("INSERT INTO genre (name) VALUES ('Ação') ON CONFLICT (name) DO NOTHING");
    // session.createQuery("INSERT INTO genre (name) VALUES ('Aventura')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Comédia')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Drama')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Fantasia')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Mistério')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Romance')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Sci-Fi')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Suspense')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Terror')", PgGenre.class).executeUpdate();
    // session.createQuery("INSERT INTO genre (name) VALUES ('Animação')", PgGenre.class).executeUpdate();
    session.getTransaction().commit();
  }
}
