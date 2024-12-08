package com.cinema.domain.errors.sale;

public class MovieSessionAlreadyShown extends Exception {
  public MovieSessionAlreadyShown(String sessionDate) {
    super("Filme já exibido na data " + sessionDate);
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
