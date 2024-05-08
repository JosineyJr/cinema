package com.cinema.infra.db.postgres.entities.users;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity(name = "client")
public class PgClient extends PgPerson {

  @ManyToMany
  @JoinTable(name = "client_movie_preferences", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private Set<PgGenre> moviesPreferences;

  public PgClient() {
  }

  public PgClient(String firstName, String lastName, String CPF, Set<PgGenre> moviesPreferences) {
    super(firstName, lastName, CPF);
    this.moviesPreferences = moviesPreferences;
  }

  public Set<PgGenre> getMoviesPreferences() {
    return this.moviesPreferences;
  }

  public void setMoviesPreferences(Set<PgGenre> moviesPreferences) {
    this.moviesPreferences = moviesPreferences;
  }
}
