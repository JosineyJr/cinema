package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Client;

public interface IFindClientByCPFRepository {
  public Client findClientByCPF(String cpf);
}
