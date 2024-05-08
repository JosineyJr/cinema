package com.cinema.infra.db.postgres.entities.users;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

  @Column(nullable = false, unique = true)
  private String CPF;

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCPF() {
    return this.CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

}
