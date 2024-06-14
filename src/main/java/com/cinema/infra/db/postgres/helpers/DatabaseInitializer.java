package com.cinema.infra.db.postgres.helpers;

import java.util.List;

import org.hibernate.Session;

import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
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

  public static void initMovie(Session session) {
    session.beginTransaction();

    session.createNativeQuery(
        "INSERT INTO movie (id, director, duration, minimum_age, synopsis, title, genre_id) VALUES ('21098561-b26e-45e8-9572-65afb4f08b2d', 'Gru', 90, 0, 'BANANA', 'Minions', 'de31fabc-7628-4e31-a3ab-fd6eb360db21') ON CONFLICT (id) DO NOTHING",
        PgMovie.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO movie (id, director, duration, minimum_age, synopsis, title, genre_id) VALUES ('7fbe34ba-73ed-4bbb-bbba-c7927f936516', 'Nolan', 120, 14, 'Ação', 'Inception', 'fbc4db0c-1386-4d7c-88b3-6e1aa8de2d29') ON CONFLICT (id) DO NOTHING",
        PgMovie.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO movie (id, director, duration, minimum_age, synopsis, title, genre_id) VALUES ('686d8adf-5267-4bcb-b268-c238b8abe70b', 'Wan', 120, 16, 'Terror', 'Invocação do Mal', 'f4d40505-2aea-43e9-8001-a2cc0784c7ba') ON CONFLICT (id) DO NOTHING",
        PgMovie.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO movie (id, director, duration, minimum_age, synopsis, title, genre_id) VALUES ('7018100a-dac9-4984-843f-f76cbc38f6c8', 'George Lucas', 120, 16, 'WAGRRRRWWGAHHHHWWWRRGGAWWWWWWRR', 'Star Wars: The Empire Strikes Back', 'f6290685-0972-4cb9-8ccc-3542f288483d') ON CONFLICT (id) DO NOTHING",
        PgMovie.class).executeUpdate();

    session.getTransaction().commit();
  }

  public static void initCinemaHall(Session session) {
    session.beginTransaction();

    session.createNativeQuery(
        "INSERT INTO cinema_hall (id, name, capacity) VALUES ('b7507766-d1c8-47cf-8870-8d73b4dcf0f6', 'Sala 1', 100) ON CONFLICT (name) DO NOTHING",
        PgCinemaHall.class)
        .executeUpdate();

    session.createNativeQuery(
        "INSERT INTO cinema_hall (id, name, capacity) VALUES ('76405e8b-98cf-4d7a-b654-f7bd59ce3b26', 'Sala 2', 100) ON CONFLICT (name) DO NOTHING",
        PgCinemaHall.class)
        .executeUpdate();

    session.createNativeQuery(
        "INSERT INTO cinema_hall (id, name, capacity) VALUES ('579cad6a-373f-468c-ba91-fb83daeb8ad7', 'Sala 3', 100) ON CONFLICT (name) DO NOTHING",
        PgCinemaHall.class)
        .executeUpdate();
    session.createNativeQuery(
        "INSERT INTO cinema_hall (id, name, capacity) VALUES ('4480d232-64a8-4b1b-bc0d-c0cc019d80ca', 'Sala 4', 100) ON CONFLICT (name) DO NOTHING",
        PgCinemaHall.class)
        .executeUpdate();

    session.createNativeQuery(
        "INSERT INTO cinema_hall (id, name, capacity) VALUES ('b99ef0c4-7cf4-4195-bd96-058413519fe0', 'Sala 5', 100) ON CONFLICT (name) DO NOTHING",
        PgCinemaHall.class)
        .executeUpdate();

    session.getTransaction().commit();
  }

  public static void initProduct(Session session) {
    session.beginTransaction();

    session
        .createNativeQuery(
            "INSERT INTO product (id, name, price) VALUES ('6d7dff0a-a9e6-4002-9e53-5f97be86eb43', 'Pipoca', 10.0) ON CONFLICT (id) DO NOTHING",
            PgProduct.class)
        .executeUpdate();

    session.createNativeQuery(
        "INSERT INTO product (id, name, price) VALUES ('e58d3000-c05f-435a-8571-d7637e0e1437', 'Refrigerante', 5.0) ON CONFLICT (id) DO NOTHING",
        PgProduct.class)
        .executeUpdate();

    session.createNativeQuery(
        "INSERT INTO product (id, name, price) VALUES ('ba2c595c-553a-4acc-9720-084d292acdb9', 'Água', 3.0) ON CONFLICT (id) DO NOTHING",
        PgProduct.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO product (id, name, price) VALUES ('eb762246-ebe5-4cc0-b5e0-6c01607fed90', 'Combo', 15.0) ON CONFLICT (id) DO NOTHING",
        PgProduct.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO product (id, name, price) VALUES ('d8948068-ba03-460a-85fd-79a7e6d3a7f5', 'Combo', 20.0) ON CONFLICT (id) DO NOTHING",
        PgProduct.class).executeUpdate();

    session.getTransaction().commit();
  }

  public static void initInventory(Session session) {
    session.beginTransaction();

    session.createNativeQuery(
        "INSERT INTO inventory (id, quantity, product_id) VALUES ('dbbc1925-4c27-466c-bc9f-705c718976c4', 100, '6d7dff0a-a9e6-4002-9e53-5f97be86eb43') ON CONFLICT (id) DO NOTHING",
        PgInventory.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO inventory (id, quantity, product_id) VALUES ('1f614bd5-a4c3-453f-9e75-346c37348928', 100, 'e58d3000-c05f-435a-8571-d7637e0e1437') ON CONFLICT (id) DO NOTHING",
        PgInventory.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO inventory (id, quantity, product_id) VALUES ('492e17ef-1655-4474-b399-4c9efe84079a', 100, 'ba2c595c-553a-4acc-9720-084d292acdb9') ON CONFLICT (id) DO NOTHING",
        PgInventory.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO inventory (id, quantity, product_id) VALUES ('755e232a-5e87-4a9a-9af2-812557152979', 100, 'eb762246-ebe5-4cc0-b5e0-6c01607fed90') ON CONFLICT (id) DO NOTHING",
        PgInventory.class).executeUpdate();

    session.createNativeQuery(
        "INSERT INTO inventory (id, quantity, product_id) VALUES ('36868f19-b837-486b-acdd-667325e29899', 100, 'd8948068-ba03-460a-85fd-79a7e6d3a7f5') ON CONFLICT (id) DO NOTHING",
        PgInventory.class).executeUpdate();

    session.getTransaction().commit();
  }

  private static boolean checkConstraints(Session session) {
    String checkConstraintSql = "SELECT conname FROM pg_constraint WHERE conname = 'unique_cpf_admin'";
    List<?> constraints = session.createNativeQuery(checkConstraintSql, Object.class).list();

    return constraints.isEmpty();
  }
}
