package com.cinema.domain.contracts.repositories.movies;

import java.util.List;

import com.cinema.domain.entities.movies.Genre;

public interface IListGenresRepository {
  public List<Genre> listGenres();
}
