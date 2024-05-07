package com.cinema.infra.db.postgres.entities.users;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PgPerson {
  public PgPerson() {
  }

  public PgPerson(String firstName, String lastName, String CPF) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
  }

  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String CPF;
}
