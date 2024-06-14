package com.cinema.domain.usecases.movies.comparators;

import java.util.Comparator;

import com.cinema.domain.entities.movies.Movie;

public class MovieComparator implements Comparator<Movie> {

  @Override
  public int compare(Movie o1, Movie o2) {
    return o1.getID().compareTo(o2.getID());
  }

}
