package com.cinema.infra.db.postgres.helpers.entities.users;

import com.cinema.domain.entities.users.Admin;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Employee;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class PersonConverter implements IEntityConverter<PgPerson, Person> {
  private AdminConverter adminConverter = new AdminConverter();
  private EmployeeConverter employeeConverter = new EmployeeConverter();
  private ClientConverter clientConverter = new ClientConverter();

  @Override
  public Person convert(PgPerson source) {
    if (source instanceof PgAdmin) {
      return adminConverter.convert((PgAdmin) source);
    } else if (source instanceof PgClient) {
      return clientConverter.convert((PgClient) source);
    } else {
      return employeeConverter.convert((PgEmployee) source);
    }
  }

  @Override
  public PgPerson pgConverter(Person target) {
    if (target instanceof Admin) {
      return adminConverter.pgConverter((Admin) target);
    } else if (target instanceof Client) {
      return clientConverter.pgConverter((Client) target);
    } else {
      return employeeConverter.pgConverter((Employee) target);
    }
  }

}
