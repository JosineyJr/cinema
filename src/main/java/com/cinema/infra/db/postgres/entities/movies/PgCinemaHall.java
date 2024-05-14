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

  @Column(nullable = false)
  private int capacity;

  @Column(nullable = false)
  private String name;

  public PgCinemaHall() {
  }

  public PgCinemaHall(UUID ID, int capacity, String name) {
    this.ID = ID;
    this.capacity = capacity;
    this.name = name;
  }

  public PgCinemaHall(int capacity, String name) {
    this.capacity = capacity;
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
