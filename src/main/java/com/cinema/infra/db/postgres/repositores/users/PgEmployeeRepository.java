package com.cinema.infra.db.postgres.repositores.users;

import java.util.List;

import com.cinema.domain.contracts.repositories.users.ICreateEmployeeRepository;
import com.cinema.domain.contracts.repositories.users.IFindEmployeeByCPFRepository;
import com.cinema.domain.contracts.repositories.users.IListEmployeesRepository;
import com.cinema.domain.entities.users.Employee;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgEmployeeRepository
    extends PgRepository
    implements IFindEmployeeByCPFRepository,
    ICreateEmployeeRepository,
    IListEmployeesRepository {

  @Override
  public Employee findEmployeeByCPF(String cpf) {
    try {
      PgEmployee pgEmployee = this.session.createQuery("FROM employee e WHERE e.CPF = :cpf", PgEmployee.class)
          .setParameter("cpf", cpf)
          .getSingleResult();

      if (pgEmployee instanceof PgAdmin) {
        return ConvertEntities.convertAdmin((PgAdmin) pgEmployee);
      }
      return ConvertEntities.convertEmployee(pgEmployee);

    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void createEmployee(Employee employee) {
    this.session.persist(ConvertEntities.pgConvertEmployee(employee));
  }

  public List<Employee> listEmployees() {
    List<PgEmployee> pgEmployees = this.session.createQuery("FROM employee", PgEmployee.class).getResultList();

    return pgEmployees.stream()
        .map(pgEmployee -> ConvertEntities.convertEmployee(pgEmployee))
        .collect(java.util.stream.Collectors.toList());
  }
}
