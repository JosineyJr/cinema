package com.cinema.infra.db.postgres.entities.movies;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "cinema_hall")
public class PgCinemaHall {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false, name = "number_of_chairs")
  private int numberOfChairs;

  @Column(nullable = false)
  private String name;

  public PgCinemaHall(UUID ID, int numberOfChairs, String name) {
    this.ID = ID;
    this.numberOfChairs = numberOfChairs;
    this.name = name;
  }

  public PgCinemaHall(int numberOfChairs, String name) {
    this.numberOfChairs = numberOfChairs;
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public int getNumberOfChairs() {
    return this.numberOfChairs;
  }

  public void setNumberOfChairs(int numberOfChairs) {
    this.numberOfChairs = numberOfChairs;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
