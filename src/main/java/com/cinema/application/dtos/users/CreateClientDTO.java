package com.cinema.application.dtos.users;

import java.util.ArrayList;

public class CreateClientDTO {
  private String firstName;
  private String lastName;
  private String CPF;
  private String password;
  private String passwordConfirmation;
  private ArrayList<String> moviesPreferences;

  public CreateClientDTO(String firstName, String lastName, String CPF, String password, String passwordConfirmation,
      ArrayList<String> moviesPreferences) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
    this.moviesPreferences = moviesPreferences;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCPF() {
    return this.CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }

  public ArrayList<String> getMoviesPreferences() {
    return this.moviesPreferences;
  }

  public void setMoviesPreferences(ArrayList<String> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }

}
