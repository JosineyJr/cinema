package com.cinema.infra.db.postgres.entities.users;

import java.util.UUID;
import java.util.List;

import com.cinema.infra.db.postgres.entities.movies.PgGenre;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity(name = "client")
public class PgClient extends PgPerson {

  @ManyToMany
  @JoinTable(name = "client_movie_preferences", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private List<PgGenre> moviesPreferences;

  public PgClient() {
  }

  public PgClient(String firstName, String lastName, String CPF, String password, List<PgGenre> moviesPreferences) {
    super(firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public PgClient(UUID ID, String firstName, String lastName, String CPF, String password,
      List<PgGenre> moviesPreferences) {
    super(ID, firstName, lastName, CPF, password);
    this.moviesPreferences = moviesPreferences;
  }

  public List<PgGenre> getMoviesPreferences() {
    return this.moviesPreferences;
  }

  public void setMoviesPreferences(List<PgGenre> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
}
