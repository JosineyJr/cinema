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

import com.cinema.application.dtos.movies.CreateGenreDTO;
import com.cinema.application.dtos.movies.DeleteGenreDTO;
import com.cinema.application.dtos.movies.UpdateGenreDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.movies.CreateGenreFactory;
import com.cinema.main.factories.movies.DeleteGenreFactory;
import com.cinema.main.factories.movies.ListGenresFactory;
import com.cinema.main.factories.movies.UpdateGenreFactory;

@RestController
public class GenreRoutes {

  private final SpringAdapter<Object> listGenreAdapter;
  private final SpringAdapter<CreateGenreDTO> createGenreAdapter;
  private final SpringAdapter<DeleteGenreDTO> deleteGenreAdapter;
  private final SpringAdapter<UpdateGenreDTO> updateGenreAdapter;

  public GenreRoutes() {
    this.listGenreAdapter = new SpringAdapter<>(ListGenresFactory.make());
    this.createGenreAdapter = new SpringAdapter<>(CreateGenreFactory.make());
    this.deleteGenreAdapter = new SpringAdapter<>(DeleteGenreFactory.make());
    this.updateGenreAdapter = new SpringAdapter<>(UpdateGenreFactory.make());
  }

  @GetMapping("/genres")
  public ResponseEntity<?> getGenres() {
    return this.listGenreAdapter.adapt(null);
  }

  @PostMapping("/genres")
  public ResponseEntity<?> postGenres(@RequestBody CreateGenreDTO genre) {
    return createGenreAdapter.adapt(genre);
  }

  @DeleteMapping("/genres/{id}")
  public ResponseEntity<?> deleteGenre(@PathVariable UUID id) {
    DeleteGenreDTO genre = new DeleteGenreDTO(id);
    return deleteGenreAdapter.adapt(genre);
  }

  @PutMapping("/genres")
  public ResponseEntity<?> updateGenre(@RequestBody UpdateGenreDTO genre) {
    return updateGenreAdapter.adapt(genre);
  }
}
