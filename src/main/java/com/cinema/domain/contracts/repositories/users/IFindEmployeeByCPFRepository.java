package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Employee;

public interface IFindEmployeeByCPFRepository {
  public Employee findEmployeeByCPF(String cpf);
}
