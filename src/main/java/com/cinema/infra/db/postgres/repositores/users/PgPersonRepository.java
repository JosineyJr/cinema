package com.cinema.infra.db.postgres.repositores.users;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgPersonRepository extends PgRepository implements IFindPersonByIDRepository {

  @Override
  public Person findPersonByID(UUID personID) {
    PgPerson pgPerson = this.session.find(PgPerson.class, personID);

    if (pgPerson == null) {
      return null;
    }

    Person person = ConvertEntities.convertPerson(pgPerson);

    return person;
  }

}
