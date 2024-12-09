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

import com.cinema.application.dtos.movies.CreateCinemaHallDTO;
import com.cinema.application.dtos.movies.DeleteCinemaHallDTO;
import com.cinema.application.dtos.movies.UpdateCinemaHallDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.movies.CreateCinemaHallFactory;
import com.cinema.main.factories.movies.DeleteCinemaHallFactory;
import com.cinema.main.factories.movies.ListCinemaHallsFactory;
import com.cinema.main.factories.movies.UpdateCinemaHallFactory;

@RestController
public class CinemaHallRoutes {

  private final SpringAdapter<Object> listCinemaHallAdapter;
  private final SpringAdapter<CreateCinemaHallDTO> createCinemaHallAdapter;
  private final SpringAdapter<DeleteCinemaHallDTO> deleteCinemaHallAdapter;
  private final SpringAdapter<UpdateCinemaHallDTO> updateCinemaHallAdapter;

  public CinemaHallRoutes() {
    this.listCinemaHallAdapter = new SpringAdapter<>(ListCinemaHallsFactory.make());
    this.createCinemaHallAdapter = new SpringAdapter<>(CreateCinemaHallFactory.make());
    this.deleteCinemaHallAdapter = new SpringAdapter<>(DeleteCinemaHallFactory.make());
    this.updateCinemaHallAdapter = new SpringAdapter<>(UpdateCinemaHallFactory.make());
  }

  @GetMapping("/cinema-halls")
  public ResponseEntity<?> getCinemaHall() {
    return this.listCinemaHallAdapter.adapt(null);
  }

  @PostMapping("/cinema-hall")
  public ResponseEntity<?> postCinemaHall(@RequestBody CreateCinemaHallDTO cinemaHall) {
    return createCinemaHallAdapter.adapt(cinemaHall);
  }

  @DeleteMapping("/cinema-hall/{id}")
  public ResponseEntity<?> deleteCinemaHall(@PathVariable UUID id) {
    DeleteCinemaHallDTO cinemaHall = new DeleteCinemaHallDTO(id);
    return deleteCinemaHallAdapter.adapt(cinemaHall);
  }

  @PutMapping("/cinema-hall")
  public ResponseEntity<?> updateCinemaHall(@RequestBody UpdateCinemaHallDTO cinemaHall) {
    return updateCinemaHallAdapter.adapt(cinemaHall);
  }
}
