package com.cinema.application.errors;

public class CompareFieldsError extends Exception {
  public CompareFieldsError(String field, String fieldToCompare) {
    super("Fields " + field + " and " + fieldToCompare + " are not equal");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
