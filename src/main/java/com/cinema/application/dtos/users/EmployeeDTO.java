package com.cinema.application.dtos.users;

import java.util.UUID;

public class EmployeeDTO {
  private UUID ID;
  private String firstName;
  private String lastName;
  private String CPF;

  public EmployeeDTO(UUID ID, String firstName, String lastName, String CPF) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
  }

  public UUID getID() {
    return ID;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCPF() {
    return CPF;
  }
}
