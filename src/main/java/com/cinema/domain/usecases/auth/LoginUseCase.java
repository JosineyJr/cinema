package com.cinema.domain.usecases.auth;

import com.cinema.domain.contracts.providers.IHasherComparer;
import com.cinema.domain.contracts.repositories.users.IFindPersonByCPFRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Employee;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.enums.auth.Role;
import com.cinema.domain.errors.LoginError;

public class LoginUseCase {
  private IFindPersonByCPFRepository findPersonByCPFRepository;
  private IHasherComparer hasherComparer;

  public LoginUseCase(IFindPersonByCPFRepository findPersonByCPFRepository, IHasherComparer hasherComparer) {
    this.findPersonByCPFRepository = findPersonByCPFRepository;
    this.hasherComparer = hasherComparer;
  }

  public String execute(String CPF, String password) throws LoginError {
    Person person = findPersonByCPFRepository.findPersonByCPF(CPF);

    if (person == null) {
      throw new LoginError();
    }

    boolean passwordMatched = hasherComparer.compare(password, person.getPassword());

    if (!passwordMatched) {
      throw new LoginError();
    }

    if (person instanceof Client) {
      return Role.CLIENT.toString();
    } else if (person instanceof Employee) {
      return Role.EMPLOYEE.toString();
    } else {
      return Role.ADMIN.toString();
    }
  }
}
