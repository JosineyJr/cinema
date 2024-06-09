package com.cinema.application.dtos.users;

import java.util.ArrayList;

/**
 * Represents a data transfer object for creating a client.
 */
public class CreateClientDTO {
  private String firstName;
  private String lastName;
  private String CPF;
  private String password;
  private String passwordConfirmation;
  private ArrayList<String> moviesPreferences;

  /**
   * Constructs a new CreateClientDTO object with the specified parameters.
   *
   * @param firstName             the first name of the client
   * @param lastName              the last name of the client
   * @param CPF                   the CPF (Brazilian identification number) of the client
   * @param password              the password of the client
   * @param passwordConfirmation  the password confirmation of the client
   * @param moviesPreferences     the list of movie preferences of the client
   */
  public CreateClientDTO(String firstName, String lastName, String CPF, String password, String passwordConfirmation,
      ArrayList<String> moviesPreferences) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
    this.moviesPreferences = moviesPreferences;
  }

  /**
   * Gets the first name of the client.
   *
   * @return the first name of the client
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Sets the first name of the client.
   *
   * @param firstName the first name of the client
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the client.
   *
   * @return the last name of the client
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Sets the last name of the client.
   *
   * @param lastName the last name of the client
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the CPF (Brazilian identification number) of the client.
   *
   * @return the CPF of the client
   */
  public String getCPF() {
    return this.CPF;
  }

  /**
   * Sets the CPF (Brazilian identification number) of the client.
   *
   * @param CPF the CPF of the client
   */
  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  /**
   * Gets the password of the client.
   *
   * @return the password of the client
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of the client.
   *
   * @param password the password of the client
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the password confirmation of the client.
   *
   * @return the password confirmation of the client
   */
  public String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  /**
   * Sets the password confirmation of the client.
   *
   * @param passwordConfirmation the password confirmation of the client
   */
  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }

  /**
   * Gets the list of movie preferences of the client.
   *
   * @return the list of movie preferences of the client
   */
  public ArrayList<String> getMoviesPreferences() {
    return this.moviesPreferences;
  }

  /**
   * Sets the list of movie preferences of the client.
   *
   * @param moviesPreferences the list of movie preferences of the client
   */
  public void setMoviesPreferences(ArrayList<String> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }

}
