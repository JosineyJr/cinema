package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Person;

public interface IUpdatePersonRepository {
  void updatePerson(Person person);
}
