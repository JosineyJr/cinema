package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.Genre;

public interface IUpdateGenreRepository {
  void updateGenre(Genre genre);
}
