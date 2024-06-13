package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IUpdateMovieRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.errors.movies.GenreNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;

public class UpdateMovieUseCase {
  private IFindMovieByIDRepository findMovieByIDRepository;
  private IUpdateMovieRepository updateMovieRepository;
  private IFindGenreByIDRepository findGenreByIDRepository;

  public UpdateMovieUseCase(IFindMovieByIDRepository findMovieByIDRepository,
      IUpdateMovieRepository updateMovieRepository) {
    this.findMovieByIDRepository = findMovieByIDRepository;
    this.updateMovieRepository = updateMovieRepository;
  }

  public void execute(UUID ID, String title, String synopsis, String director, UUID genreID, int duration,
      int minimumAge) throws MovieNotFoundError, GenreNotFoundError {
    Genre genre = findGenreByIDRepository.findGenreByID(genreID);

    if (genre == null) {
      throw new GenreNotFoundError();
    }

    Movie foundMovie = this.findMovieByIDRepository.findMovieByID(ID);

    if (foundMovie == null) {
      throw new MovieNotFoundError();
    }

    Movie movie = new Movie(ID, title, synopsis, director, genre, duration, minimumAge);

    this.updateMovieRepository.updateMovie(movie);
  }
}
