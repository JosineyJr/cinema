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

  public CreateMovieUseCase(ICreateMovieRepository createMovieRepository,
      IFindGenreByIDRepository findGenreByIDRepository) {
    this.createMovieRepository = createMovieRepository;
    this.findGenreByIDRepository = findGenreByIDRepository;
  }

  /**
   * Executes the use case to create a new movie.
   *
   * @param title       the title of the movie
   * @param synopsis    the synopsis of the movie
   * @param director    the director of the movie
   * @param genreID     the ID of the genre for the movie
   * @param duration    the duration of the movie in minutes
   * @param minimumAge  the minimum age requirement for the movie
   * @throws GenreNotFoundError if the genre with the specified ID is not found
   */
  public void execute(String title, String synopsis, String director, UUID genreID, int duration, int minimumAge)
      throws GenreNotFoundError {
    Genre genre = findGenreByIDRepository.findGenreByID(genreID);

    if (genre == null) {
      throw new GenreNotFoundError();
    }

    Movie movie = new Movie(title, synopsis, director, genre, duration, minimumAge);

    createMovieRepository.createMovie(movie);
  }
}
