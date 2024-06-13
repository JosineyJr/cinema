package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IUpdateMovieRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.errors.movies.GenreNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;

/**
 * Use case for updating a movie.
 */
public class UpdateMovieUseCase {
  private IFindMovieByIDRepository findMovieByIDRepository;
  private IUpdateMovieRepository updateMovieRepository;
  private IFindGenreByIDRepository findGenreByIDRepository;

  /**
   * Constructs a new UpdateMovieUseCase with the given repositories.
   *
   * @param findMovieByIDRepository The repository for finding a movie by ID.
   * @param updateMovieRepository The repository for updating a movie.
   */
  public UpdateMovieUseCase(IFindMovieByIDRepository findMovieByIDRepository,
      IUpdateMovieRepository updateMovieRepository) {
    this.findMovieByIDRepository = findMovieByIDRepository;
    this.updateMovieRepository = updateMovieRepository;
  }

  /**
   * Executes the use case to update a movie.
   *
   * @param ID The ID of the movie to update.
   * @param title The new title of the movie.
   * @param synopsis The new synopsis of the movie.
   * @param director The new director of the movie.
   * @param genreID The ID of the new genre of the movie.
   * @param duration The new duration of the movie.
   * @param minimumAge The new minimum age requirement for the movie.
   * @throws MovieNotFoundError If the movie with the given ID is not found.
   * @throws GenreNotFoundError If the genre with the given ID is not found.
   */
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
