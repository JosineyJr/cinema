package com.cinema.infra.db.postgres.entities.movies;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "genre")
public class PgGenre {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false, unique = true)
  private String name;

  public PgGenre() {
  }

  public PgGenre(UUID ID, String name) {
    this.ID = ID;
    this.name = name;
  }

  public PgGenre(String name) {
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
