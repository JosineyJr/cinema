package com.cinema.domain.contracts.repositories.movies;

import java.time.LocalTime;
import java.util.UUID;

public interface IFindMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDurationRepository {
  public boolean findMovieSessionByCinemaHallIDAndSessionStartTimeAndMovieDuration(UUID cinemaHallID,
      LocalTime SessionStartTime, int movieDuration);
}
