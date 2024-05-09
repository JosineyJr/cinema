package com.cinema.infra.db.postgres.entities.users;

import jakarta.persistence.Entity;

@Entity(name = "employee")
public class PgEmployee extends PgPerson {
  public PgEmployee() {
  }

  public PgEmployee(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }
}
