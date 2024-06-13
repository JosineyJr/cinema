package com.cinema.application.dtos.users;

import java.util.UUID;

/**
 * Represents a data transfer object for updating client information.
 */
public class UpdateClientDTO {
  private UUID ID;
  private String firstName;
  private String lastName;
  private String CPF;

  /**
   * Constructs a new UpdateClientDTO object with the specified parameters.
   *
   * @param ID        the unique identifier of the client
   * @param firstName the first name of the client
   * @param lastName  the last name of the client
   * @param CPF       the CPF (Brazilian individual taxpayer registry) of the client
   */
  public UpdateClientDTO(UUID ID, String firstName, String lastName, String CPF) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
  }

  /**
   * Returns the unique identifier of the client.
   *
   * @return the unique identifier of the client
   */
  public UUID getID() {
    return ID;
  }

  /**
   * Returns the first name of the client.
   *
   * @return the first name of the client
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the client.
   *
   * @return the last name of the client
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the CPF of the client.
   *
   * @return the CPF of the client
   */
  public String getCPF() {
    return CPF;
  }
}
