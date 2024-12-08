package com.cinema.infra.db.postgres.helpers.entities.users;

import com.cinema.domain.entities.users.Employee;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class EmployeeConverter implements IEntityConverter<PgEmployee, Employee> {
  @Override
  public Employee convert(PgEmployee employee) {
    return new Employee(
        employee.getID(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getCPF(),
        employee.getPassword());
  }

  @Override
  public PgEmployee pgConverter(Employee target) {
    return new PgEmployee(
        target.getID(),
        target.getFirstName(),
        target.getLastName(),
        target.getCPF(),
        target.getPassword());
  }
}