package com.cinema.domain.contracts.providers;

public interface IHasherComparer {
  public boolean compare(String text, String textToCompare);
}
