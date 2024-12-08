package com.cinema.domain.contracts.repositories.movies;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository {
  public boolean findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(UUID cinemaHallID,
      LocalDateTime SessionStartTime, int movieDuration);
}
