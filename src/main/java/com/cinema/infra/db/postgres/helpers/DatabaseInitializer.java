package com.cinema.infra.db.postgres.helpers;

import java.util.List;

import org.hibernate.Session;

import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;

public class DatabaseInitializer {
  public static void initGenre(Session session) {
    session.beginTransaction();

    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('fbc4db0c-1386-4d7c-88b3-6e1aa8de2d29','Ação') ON CONFLICT (name) DO NOTHING",
        PgGenre.class)
        .executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('fbc4db0c-1386-412c-88b3-6e1aa8de2321','Aventura') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('fbc4db0c-1386-4d7c-88b3-6e1aa8de4d32','Comédia') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('65e87acd-9187-4ca1-801b-13e8b3ff690a','Drama') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('fbc4db0c-1386-4d7c-88b3-6e1aa8ded2a1','Fantasia') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('89d184df-8794-4816-954e-336fc154568a','Mistério') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('60827b8b-5e67-4da7-bb57-6b44141a337c','Romance') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('f6290685-0972-4cb9-8ccc-3542f288483d','Sci-Fi') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('e0cbdd78-603e-4846-8d1e-c9a4da0c682c','Suspense') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('f4d40505-2aea-43e9-8001-a2cc0784c7ba','Terror') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO genre (id, name) VALUES ('de31fabc-7628-4e31-a3ab-fd6eb360db21','Animação') ON CONFLICT (name) DO NOTHING",
        PgGenre.class).executeUpdate();

    session.getTransaction().commit();
  }

  public static void initUsers(Session session) {
    session.beginTransaction();

    boolean constraints = checkConstraints(session);
    if (constraints) {
      session.createNativeQuery("ALTER TABLE admin ADD CONSTRAINT unique_cpf_admin UNIQUE (cpf)", PgAdmin.class)
          .executeUpdate();

      session
          .createNativeQuery("ALTER TABLE employee ADD CONSTRAINT unique_cpf_employee UNIQUE (cpf)", PgEmployee.class)
          .executeUpdate();

      session.createNativeQuery("ALTER TABLE client ADD CONSTRAINT unique_cpf_client UNIQUE (cpf)", PgClient.class)
          .executeUpdate();
    }

    session.createNativeQuery(
        "INSERT INTO admin (id, cpf, first_name, last_name, password) VALUES ('70e4dd2f-ce7c-4f3c-afee-00d64f87dbde', '12461414009', 'Master', 'User', '$2a$12$UWXHGddEHi6tLhutfDdUf.jbhg9iLXUYbNUf0lrxUHYa702IRiVLa') ON CONFLICT (cpf) DO NOTHING",
        PgAdmin.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO employee (id, cpf, first_name, last_name, password) VALUES ('8d83c944-1341-4eb8-8fa0-3e4c7df05a09', '91694536084', 'Func', '1', '$2a$12$BfAuYBB8nmjjLAGxzpyrKOo2NL65O5ngEDt3yYne3AABXNSw5Vedm') ON CONFLICT (cpf) DO NOTHING",
        PgEmployee.class).executeUpdate();
    session.createNativeQuery(
        "INSERT INTO employee (id, cpf, first_name, last_name, password) VALUES ('848d720d-cf11-4ca9-967f-4adf06be5510', '29838176010', 'Func', '2', '$2a$12$0oQ7D5W1i6bVmoL4VWJmueJWVg64sNeDjiA2cg.eA.P96YoB.i57a') ON CONFLICT (cpf) DO NOTHING",
        PgEmployee.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO client (id, cpf, first_name, last_name, password) VALUES ('1f533520-06b7-4a08-ab7e-9d9adce97517', '79101926063', 'Cliente', '1', '$2a$12$CXXlqRCEZ5dar2QfgWi/qugmdu7glmVIJerxaKZLAhl5H1gRmetGi') ON CONFLICT (cpf) DO NOTHING",
        PgClient.class)
        .executeUpdate();

    session.getTransaction().commit();
  }

  private static boolean checkConstraints(Session session) {
    String checkConstraintSql = "SELECT conname FROM pg_constraint WHERE conname = 'unique_cpf_admin'";
    List<?> constraints = session.createNativeQuery(checkConstraintSql, Object.class).list();

    return constraints.isEmpty();
  }
}
