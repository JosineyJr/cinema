package com.cinema.domain.contracts.repositories.users;

import java.util.UUID;

import com.cinema.domain.entities.users.Person;

public interface IFindPersonByIDRepository {
  public Person findPersonByID(UUID personID);
}
