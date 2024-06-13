package com.cinema.domain.usecases.users;

import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.contracts.repositories.users.IUpdateClientRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.errors.users.PersonNotFoundError;

public class UpdateClientUseCase {
  private IFindPersonByIDRepository findPersonByIDRepository;
  private IUpdateClientRepository updateClientRepository;

  public UpdateClientUseCase(IFindPersonByIDRepository findPersonByIDRepository,
      IUpdateClientRepository updateClientRepository) {
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.updateClientRepository = updateClientRepository;
  }

  /**
   * Executes the use case to update a client.
   *
   * @param client The client to be updated.
   * @throws PersonNotFoundError If the client with the given ID is not found.
   */
  public void execute(Client client) throws PersonNotFoundError {
    if (this.findPersonByIDRepository.findPersonByID(client.getID()) == null) {
      throw new PersonNotFoundError();
    }

    this.updateClientRepository.updateClient(client);
  }
}
