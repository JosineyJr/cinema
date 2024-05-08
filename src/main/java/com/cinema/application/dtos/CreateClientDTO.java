package com.cinema.application.dtos;

import java.util.ArrayList;

public class CreateClientDTO {
  private String firstName;
  private String lastName;
  private String CPF;
  private ArrayList<String> moviesPreferences;

  public CreateClientDTO(String firstName, String lastName, String CPF, ArrayList<String> moviesPreferences) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
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

  public ArrayList<String> getMoviesPreferences() {
    return this.moviesPreferences;
  }

  public void setMoviesPreferences(ArrayList<String> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
  
}
