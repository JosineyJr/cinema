package com.cinema.application.errors;

public class CompareFieldsError extends Exception {
  public CompareFieldsError(String field, String fieldToCompare) {
    super("Os campos " + field + " e " + fieldToCompare + " não são iguais.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
