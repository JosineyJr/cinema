package com.cinema.application.controllers.users;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.ClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.usecases.users.ListClientsUseCase;

public class ListClientsController extends Controller<Object> {
  private ListClientsUseCase listClientsUseCase;

  public ListClientsController(ListClientsUseCase listClientsUseCase) {
    this.listClientsUseCase = listClientsUseCase;
  }

  /**
   * Performs the operation to list all clients.
   *
   * @param object The input object (not used in this implementation).
   * @return A Response object containing a list of ClientDTOs if successful, or an error response if an exception occurs.
   */
  public Response<?> perform(Object object) {
    try {
      List<Client> clients = this.listClientsUseCase.listClients();

      List<ClientDTO> clientDTOs = clients.stream()
          .map(
              client -> new ClientDTO(client.getID(), client.getFirstName(), client.getLastName(), client.getCPF()))
          .toList();

      return ResponseFactory.ok(clientDTOs);
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

    /**
     * Builds and returns a list of validators for the given object.
     *
     * @param object The object for which validators need to be built.
     * @return An ArrayList of IValidator objects representing the validators for the given object.
     */
    @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
