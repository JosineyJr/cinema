package com.cinema.main.routes.movies;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.movies.CreateMovieDTO;
import com.cinema.application.dtos.movies.DeleteMovieDTO;
import com.cinema.application.dtos.movies.UpdateMovieDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.movies.CreateMovieFactory;
import com.cinema.main.factories.movies.DeleteMovieFactory;
import com.cinema.main.factories.movies.ListMoviesFactory;
import com.cinema.main.factories.movies.UpdateMovieFactory;

@RestController
public class MovieRoutes {

  private final SpringAdapter<Object> listMovieAdapter;
  private final SpringAdapter<CreateMovieDTO> createMovieAdapter;
  private final SpringAdapter<DeleteMovieDTO> deleteMovieAdapter;
  private final SpringAdapter<UpdateMovieDTO> updateMovieAdapter;

  public MovieRoutes() {
    this.listMovieAdapter = new SpringAdapter<>(ListMoviesFactory.make());
    this.createMovieAdapter = new SpringAdapter<>(CreateMovieFactory.make());
    this.deleteMovieAdapter = new SpringAdapter<>(DeleteMovieFactory.make());
    this.updateMovieAdapter = new SpringAdapter<>(UpdateMovieFactory.make());
  }

  @GetMapping("/movies")
  public ResponseEntity<?> getMovies() {
    return this.listMovieAdapter.adapt(null);
  }

  @PostMapping("/movies")
  public ResponseEntity<?> postMovie(@RequestBody CreateMovieDTO movie) {
    return createMovieAdapter.adapt(movie);
  }

  @DeleteMapping("/movies/{id}")
  public ResponseEntity<?> deleteMovie(@PathVariable UUID id) {
    DeleteMovieDTO movie = new DeleteMovieDTO(id);
    return deleteMovieAdapter.adapt(movie);
  }

  @PutMapping("/movies")
  public ResponseEntity<?> updateMovie(@RequestBody UpdateMovieDTO movie) {
    return updateMovieAdapter.adapt(movie);
  }
}
