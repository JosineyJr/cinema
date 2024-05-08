package com.cinema.domain.entities.users;

import java.util.ArrayList;
import java.util.UUID;

public class Client extends Person {
  private ArrayList<Genre> moviesPreferences;

  public Client(UUID ID, String firstName, String lastName, String CPF, String password, ArrayList<Genre> moviesPreferences) {
    super(ID, firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public Client(String firstName, String lastName, String CPF, String password, ArrayList<Genre> moviesPreferences) {
    super(firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public ArrayList<Genre> getMoviesPreferences() {
    return moviesPreferences;
  }

  public void setMoviesPreferences(ArrayList<Genre> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
}
