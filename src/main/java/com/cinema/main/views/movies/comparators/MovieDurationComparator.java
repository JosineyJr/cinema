package com.cinema.main.views.movies.comparators;

import java.util.Comparator;

import com.cinema.application.dtos.movies.MovieDTO;

public class MovieDurationComparator implements Comparator<MovieDTO> {

  @Override
  public int compare(MovieDTO o1, MovieDTO o2) {
    return Integer.compare(o1.getDuration(), o2.getDuration());
  }

}
