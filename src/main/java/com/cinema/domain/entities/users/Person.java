package com.cinema.domain.entities.users;

import java.util.UUID;

public class Person {
  private UUID ID;
  private String firstName;
  private String lastName;
  private String CPF;

  public Person(UUID ID, String firstName, String lastName, String CPF) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
  }

  public Person(String firstName, String lastName, String CPF) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;

    // this.ID = UUID.randomUUID();
  }

  public UUID getID() {
    return ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCPF() {
    return CPF;
  }
}
