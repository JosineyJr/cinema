package com.cinema.domain.usecases.users;

import java.util.ArrayList;

import com.cinema.domain.contracts.providers.IHasher;
import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.contracts.repositories.users.IFindClientByCPFRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.errors.users.ClientAlreadyExistsError;

public class CreateClientUseCase {
  private ICreateClientRepository createClientRepository;
  private IFindClientByCPFRepository findClientByCPFRepository;
  private IHasher hasher;

  public CreateClientUseCase(ICreateClientRepository createClientRepository,
      IFindClientByCPFRepository findClientByCPFRepository, IHasher hasher) {
    this.createClientRepository = createClientRepository;
    this.findClientByCPFRepository = findClientByCPFRepository;
    this.hasher = hasher;
  }

  /**
   * Executes the use case to create a new client.
   *
   * @param firstName          The first name of the client.
   * @param lastName           The last name of the client.
   * @param CPF                The CPF (Brazilian identification number) of the client.
   * @param password           The password of the client.
   * @param moviesPreferences  The list of movie preferences of the client.
   * @throws ClientAlreadyExistsError  If a client with the given CPF already exists.
   */
  public void execute(
      String firstName,
      String lastName,
      String CPF,
      String password,
      ArrayList<String> moviesPreferences) throws ClientAlreadyExistsError {

    if (findClientByCPFRepository.findClientByCPF(CPF) != null) {
      throw new ClientAlreadyExistsError();
    }

    String hashedPassword = this.hasher.hash(password);

    ArrayList<Genre> genres = new ArrayList<Genre>();

    for (String moviePreference : moviesPreferences) {
      genres.add(new Genre(moviePreference));
    }

    Client client = new Client(firstName, lastName, CPF, hashedPassword, genres);
    createClientRepository.createClient(client);
  }
}
