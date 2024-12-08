package com.cinema.domain.contracts.repositories.users;

import java.util.List;

import com.cinema.domain.entities.users.Client;

public interface IListClientsRepository {
  List<Client> listClients();
}
