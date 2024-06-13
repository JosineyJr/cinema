package com.cinema.domain.entities.users;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.movies.Genre;

public class Client extends Person {
  private List<Genre> moviesPreferences;

  public Client(UUID ID, String firstName, String lastName, String CPF, String password,
      List<Genre> moviesPreferences) {
    super(ID, firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public Client(UUID ID, String firstName, String lastName, String CPF) {
    super(ID, firstName, lastName, CPF);
  }

  public Client(String firstName, String lastName, String CPF, String password, List<Genre> moviesPreferences) {
    super(firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public List<Genre> getMoviesPreferences() {
    return moviesPreferences;
  }

  public void setMoviesPreferences(List<Genre> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
}
