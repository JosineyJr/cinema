package com.cinema.domain.entities.users;

import java.util.UUID;

public class Admin extends Employee {
  public Admin(UUID ID, String firstName, String lastName, String CPF, String password) {
    super(ID, firstName, lastName, CPF, password);
  }

  public Admin(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }
}
