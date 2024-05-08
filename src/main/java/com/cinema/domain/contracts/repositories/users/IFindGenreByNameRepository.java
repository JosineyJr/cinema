package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Genre;

public interface IFindGenreByNameRepository {
  public Genre findGenreByName(String genre);
}
