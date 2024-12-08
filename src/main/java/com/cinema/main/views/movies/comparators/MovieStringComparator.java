package com.cinema.main.views.movies.comparators;

import java.util.Comparator;

public class MovieStringComparator implements Comparator<String> {

  @Override
  public int compare(String compare, String toCompare) {
    return compare.compareTo(toCompare);
  }
}
