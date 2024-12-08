package com.cinema.domain.usecases.users;

import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.contracts.repositories.users.IUpdatePersonRepository;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.users.PersonNotFoundError;

public class UpdatePersonUseCase {
  private IFindPersonByIDRepository findPersonByIDRepository;
  private IUpdatePersonRepository updatePersonRepository;

  public UpdatePersonUseCase(IFindPersonByIDRepository findPersonByIDRepository,
      IUpdatePersonRepository updatePersonRepository) {
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.updatePersonRepository = updatePersonRepository;
  }

  /**
   * Executes the use case to update a client.
   *
   * @param object The person to be updated.
   * @throws PersonNotFoundError If the client with the given ID is not found.
   */
  public void execute(Person object) throws PersonNotFoundError {
    Person person = this.findPersonByIDRepository.findPersonByID(object.getID());

    if (person == null) {
      throw new PersonNotFoundError();
    }

    person.setCPF(object.getCPF());
    person.setFirstName(object.getFirstName());
    person.setLastName(object.getLastName());

    this.updatePersonRepository.updatePerson(person);
  }
}
