package com.cinema.infra.db.postgres.helpers.entities.users;

import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.users.Client;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.GenreConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ClientConverter implements IEntityConverter<PgClient, Client> {
  GenreConverter genreConverter = new GenreConverter();

  @Override
  public Client convert(PgClient client) {
    List<Genre> moviePreferences = client.getMoviesPreferences().stream()
        .map(genre -> this.genreConverter.convert(genre))
        .collect(Collectors.toList());

    return new Client(
        client.getID(),
        client.getFirstName(),
        client.getLastName(),
        client.getCPF(),
        client.getPassword(),
        moviePreferences);
  }

  @Override
  public PgClient pgConverter(Client target) {
    List<PgGenre> moviePreferences = target.getMoviesPreferences().stream()
        .map(genre -> this.genreConverter.pgConverter(genre))
        .collect(Collectors.toList());

    return new PgClient(
        target.getID(),
        target.getFirstName(),
        target.getLastName(),
        target.getCPF(),
        target.getPassword(),
        moviePreferences);
  }
}
