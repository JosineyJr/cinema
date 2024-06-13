package com.cinema.domain.usecases.users;

import java.util.List;

import com.cinema.domain.contracts.repositories.users.IListClientsRepository;
import com.cinema.domain.entities.users.Client;

public class ListClientsUseCase {
  private IListClientsRepository listClientsRepository;

  /**
   * Constructs a new ListClientsUseCase with the specified repository.
   *
   * @param listClientsRepository the repository for accessing client data
   */
  public ListClientsUseCase(IListClientsRepository listClientsRepository) {
    this.listClientsRepository = listClientsRepository;
  }

  /**
   * Retrieves a list of clients.
   *
   * @return the list of clients
   */
  public List<Client> listClients() {
    return this.listClientsRepository.listClients();
  }
}
