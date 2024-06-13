package com.cinema.application.dtos.users;

import java.util.UUID;

public class ClientDTO {
  private UUID ID;
  private String firstName;
  private String lastName;
  private String CPF;

  public ClientDTO(UUID ID, String firstName, String lastName, String CPF) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
  }

  public UUID getID() {
    return this.ID;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getCPF() {
    return this.CPF;
  }
}
