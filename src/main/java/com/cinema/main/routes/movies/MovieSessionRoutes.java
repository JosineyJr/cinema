package com.cinema.main.routes.movies;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.movies.CreateMovieSessionDTO;
import com.cinema.application.dtos.movies.DeleteMovieSessionDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.movies.CreateMovieSessionFactory;
import com.cinema.main.factories.movies.DeleteMovieSessionFactory;
import com.cinema.main.factories.movies.ListMovieSessionsFactory;

@RestController
public class MovieSessionRoutes {

  private final SpringAdapter<Object> listMovieSessionAdapter;
  private final SpringAdapter<CreateMovieSessionDTO> createMovieSessionAdapter;
  private final SpringAdapter<DeleteMovieSessionDTO> deleteMovieSessionAdapter;

  public MovieSessionRoutes() {
    this.listMovieSessionAdapter = new SpringAdapter<>(ListMovieSessionsFactory.make());
    this.createMovieSessionAdapter = new SpringAdapter<>(CreateMovieSessionFactory.make());
    this.deleteMovieSessionAdapter = new SpringAdapter<>(DeleteMovieSessionFactory.make());
  }

  @GetMapping("/movie-sessions")
  public ResponseEntity<?> getMovieSession() {
    return this.listMovieSessionAdapter.adapt(null);
  }

  @PostMapping("/movie-session")
  public ResponseEntity<?> postMovieSession(@RequestBody CreateMovieSessionDTO MovieSession) {
    return createMovieSessionAdapter.adapt(MovieSession);
  }

  @DeleteMapping("/movie-session/{id}")
  public ResponseEntity<?> deleteMovieSession(@PathVariable UUID id) {
    DeleteMovieSessionDTO MovieSession = new DeleteMovieSessionDTO(id);
    return deleteMovieSessionAdapter.adapt(MovieSession);
  }
}
