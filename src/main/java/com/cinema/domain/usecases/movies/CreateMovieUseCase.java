package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.ICreateMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.errors.movies.GenreNotFoundError;

public class CreateMovieUseCase {
  private ICreateMovieRepository createMovieRepository;
  private IFindGenreByIDRepository findGenreByIDRepository;

  public CreateMovieUseCase(ICreateMovieRepository createMovieRepository, IFindGenreByIDRepository findGenreByIDRepository) {
    this.createMovieRepository = createMovieRepository;
    this.findGenreByIDRepository = findGenreByIDRepository;
  }

  public void execute(String title, String description, String director, UUID genreID, int duration, int minimumAge)
      throws GenreNotFoundError {
    Genre genre = findGenreByIDRepository.findGenreByID(genreID);

    if (genre == null) {
      throw new GenreNotFoundError();
    }

    Movie movie = new Movie(title, description, director, genre, duration, minimumAge);

    createMovieRepository.createMovie(movie);
  }
}
