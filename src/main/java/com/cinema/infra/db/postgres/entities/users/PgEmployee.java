package com.cinema.infra.db.postgres.entities.users;

import java.util.UUID;

import jakarta.persistence.Entity;

@Entity(name = "employee")
public class PgEmployee extends PgPerson {
  public PgEmployee() {
  }

  public PgEmployee(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }

  public PgEmployee(UUID ID, String firstName, String lastName, String CPF, String password) {
    super(ID, firstName, lastName, CPF, password);
  }
}
