package com.cinema.application.dtos.users;

import java.util.UUID;

import com.cinema.domain.enums.auth.Role;

public class PersonDTO {
  private UUID ID;
  private String name;
  private String CPF;
  private Role role;

  public PersonDTO(UUID ID, String firstName, String lastName, String CPF, Role role) {
    this.ID = ID;
    this.name = firstName + " " + lastName;
    this.CPF = CPF;
    this.role = role;
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

  public String getCPF() {
    return this.CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
