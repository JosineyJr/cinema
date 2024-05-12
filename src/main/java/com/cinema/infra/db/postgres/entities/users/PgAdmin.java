package com.cinema.infra.db.postgres.entities.users;

import jakarta.persistence.Entity;

@Entity(name = "admin")
public class PgAdmin extends PgEmployee {
  public PgAdmin() {
  }

  public PgAdmin(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }
}
