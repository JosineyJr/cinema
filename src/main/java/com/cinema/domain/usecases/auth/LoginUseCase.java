package com.cinema.domain.usecases.auth;

import com.cinema.domain.contracts.providers.IHasherComparer;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.contracts.repositories.users.IFindEmployeeByCPFRepository;
import com.cinema.domain.entities.users.Admin;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.enums.auth.Role;
import com.cinema.domain.errors.auth.LoginError;

public class LoginUseCase {
  private IFindClientByCPFRepository findClientByCPFRepository;
  private IFindEmployeeByCPFRepository findEmployeeByCPFRepository;
  private IHasherComparer hasherComparer;

  public LoginUseCase(IFindClientByCPFRepository findClientByCPFRepository,
      IFindEmployeeByCPFRepository findEmployeeByCPFRepository, IHasherComparer hasherComparer) {
    this.findClientByCPFRepository = findClientByCPFRepository;
    this.findEmployeeByCPFRepository = findEmployeeByCPFRepository;
    this.hasherComparer = hasherComparer;
  }

  public String execute(String CPF, String password, boolean isEmployee) throws LoginError {
    Person person = isEmployee ? this.findEmployeeByCPFRepository.findEmployeeByCPF(CPF)
        : this.findClientByCPFRepository.findClientByCPF(CPF);

    if (person == null || !hasherComparer.compare(password, person.getPassword())) {
      throw new LoginError();
    }

    if (person instanceof Client) {
      return Role.CLIENT.toString();
    }

    if (person instanceof Admin) {
      return Role.ADMIN.toString();
    }

    return Role.EMPLOYEE.toString();
  }
}
