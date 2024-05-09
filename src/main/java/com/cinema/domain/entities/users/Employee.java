package com.cinema.domain.entities.users;

import java.util.UUID;

public class Employee extends Person {
  public Employee(UUID ID, String firstName, String lastName, String CPF, String password) {
    super(ID, firstName, lastName, CPF, password);
  }

  public Employee(String firstName, String lastName, String CPF, String password) {
    super(firstName, lastName, CPF, password);
  }
}
