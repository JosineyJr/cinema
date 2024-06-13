package com.cinema.infra.db.postgres.repositores.users;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.contracts.repositories.users.IListPersonsRepository;
import com.cinema.domain.contracts.repositories.users.IUpdatePersonRepository;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

/**
 * Repository class for managing persons in the PostgreSQL database.
 */
public class PgPersonRepository extends PgRepository implements IFindPersonByIDRepository, IListPersonsRepository, IUpdatePersonRepository {

  /**
   * Finds a person by their ID.
   *
   * @param personID The ID of the person to find.
   * @return The person with the specified ID, or null if not found.
   */
  @Override
  public Person findPersonByID(UUID personID) {
    PgPerson pgPerson = this.session.find(PgPerson.class, personID);

    if (pgPerson == null) {
      return null;
    }

    Person person = ConvertEntities.convertPerson(pgPerson);

    return person;
  }

  /**
   * Retrieves a list of all persons from the database.
   *
   * @return a list of Person objects representing the persons in the database
   */
  @Override
  public List<Person> listPersons() {
    List<PgPerson> pgPersons = this.session.createQuery("from person", PgPerson.class).getResultList();

    return pgPersons.stream().map(pgPerson -> {
      return ConvertEntities.convertPerson(pgPerson);
    }).toList();
  }

  /**
   * Updates a person in the database.
   *
   * @param person The person object to be updated.
   */
  public void updatePerson(Person person){
    PgPerson pgPerson = ConvertEntities.pgConvertPerson(person);

    this.session.merge(pgPerson);
  }
}
