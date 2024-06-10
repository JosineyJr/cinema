package com.cinema.infra.db.postgres.repositores.users;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.contracts.repositories.users.IListPersonsRepository;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgPersonRepository extends PgRepository implements IFindPersonByIDRepository, IListPersonsRepository {

  @Override
  public Person findPersonByID(UUID personID) {
    PgPerson pgPerson = this.session.find(PgPerson.class, personID);

    if (pgPerson == null) {
      return null;
    }

    Person person = ConvertEntities.convertPerson(pgPerson);

    return person;
  }

  @Override
  public List<Person> listPersons() {
    List<PgPerson> pgPersons = this.session.createQuery("from person", PgPerson.class).getResultList();

    return pgPersons.stream().map(pgPerson -> {
      return ConvertEntities.convertPerson(pgPerson);
    }).toList();
  }

}
