package com.cinema.infra.db.postgres.entities.users;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "client")
public class PgClient extends PgPerson {
  public PgClient() {
    super();
  }

  public PgClient(String firstName, String lastName, String CPF, ArrayList<String> moviesPreferences) {
    super(firstName, lastName, CPF);
    this.moviesPreferences = moviesPreferences;
  }

  @Column(nullable = true)
  private ArrayList<String> moviesPreferences;
}
