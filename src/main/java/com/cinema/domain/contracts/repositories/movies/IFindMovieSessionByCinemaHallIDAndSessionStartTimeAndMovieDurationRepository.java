package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;
import java.time.LocalDateTime;

public interface IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository {
  public boolean findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(UUID cinemaHallID,
      LocalDateTime SessionStartTime, int movieDuration);
}
