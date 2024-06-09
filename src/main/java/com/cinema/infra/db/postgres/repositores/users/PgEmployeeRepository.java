package com.cinema.infra.db.postgres.repositores.users;

import com.cinema.domain.contracts.repositories.users.ICreateEmployeeRepository;
import com.cinema.domain.contracts.repositories.users.IFindEmployeeByCPFRepository;
import com.cinema.domain.entities.users.Employee;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgEmployeeRepository extends PgRepository
    implements IFindEmployeeByCPFRepository, ICreateEmployeeRepository {

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
}
