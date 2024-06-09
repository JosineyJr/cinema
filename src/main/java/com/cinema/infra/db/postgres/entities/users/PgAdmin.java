package com.cinema.infra.db.postgres.entities.users;

import java.util.UUID;

import jakarta.persistence.Entity;

@Entity(name = "admin")
public class PgAdmin extends PgEmployee {
  public PgAdmin() {
  }

  public PgAdmin(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }

  public PgAdmin(UUID ID, String firstName, String lastName, String CPF, String password) {
    super(ID, firstName, lastName, CPF, password);
  }
}
