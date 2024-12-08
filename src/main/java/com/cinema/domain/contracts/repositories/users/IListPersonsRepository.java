package com.cinema.domain.contracts.repositories.users;

import java.util.List;

import com.cinema.domain.entities.users.Person;

public interface IListPersonsRepository {
  public List<Person> listPersons();
}
