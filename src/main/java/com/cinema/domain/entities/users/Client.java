package com.cinema.domain.entities.users;

import java.util.ArrayList;
import java.util.UUID;

public class Client extends Person {
  private ArrayList<String> moviesPreferences;

  public Client(UUID ID, String firstName, String lastName, String CPF, ArrayList<String> moviesPreferences) {
    super(ID, firstName, lastName, CPF);
    this.moviesPreferences = moviesPreferences;
  }

  public Client(String firstName, String lastName, String CPF, ArrayList<String> moviesPreferences) {
    super(firstName, lastName, CPF);
    this.moviesPreferences = moviesPreferences;
  }

  public ArrayList<String> getMoviesPreferences() {
    return moviesPreferences;
  }

  public void setMoviesPreferences(ArrayList<String> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
}
