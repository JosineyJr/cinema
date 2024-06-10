package com.cinema.domain.usecases.users;

import java.util.List;

import com.cinema.domain.contracts.repositories.users.IListPersonsRepository;
import com.cinema.domain.entities.users.Person;

public class ListPersonsUseCase {
  private IListPersonsRepository listPersonsRepository;

  public ListPersonsUseCase(IListPersonsRepository listPersonsRepository) {
    this.listPersonsRepository = listPersonsRepository;
  }

  public List<Person> execute() {
    return this.listPersonsRepository.listPersons();
  }
}
