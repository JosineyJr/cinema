package com.cinema.domain.usecases.users;

import java.util.ArrayList;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Genre;
import com.cinema.domain.entities.users.errors.ClientAlreadyExistsError;

public class CreateClientUseCase {
  ICreateClientRepository createClientRepository;
  IFindClientByCPFRepository findClientByCPFRepository;

  public CreateClientUseCase(ICreateClientRepository createClientRepository,
      IFindClientByCPFRepository findClientByCPFRepository) {
    this.createClientRepository = createClientRepository;
    this.findClientByCPFRepository = findClientByCPFRepository;
  }

  public void execute(
      String firstName,
      String lastName,
      String CPF,
      ArrayList<String> moviesPreferences) throws ClientAlreadyExistsError {

    Client clientFound = findClientByCPFRepository.findClientByCPF(CPF);

    if (clientFound == null) {
      ArrayList<Genre> genres = new ArrayList<Genre>();

      for (String moviePreference : moviesPreferences) {
        genres.add(new Genre(moviePreference));
      }

      Client client = new Client(firstName, lastName, CPF, genres);
      createClientRepository.createClient(client);
    } else {
      throw new ClientAlreadyExistsError();
    }
  }
}
