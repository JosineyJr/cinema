package com.cinema.application.validation;

import java.util.UUID;

import com.cinema.application.errors.InvalidUUIDError;

public class ValidateUUID implements IValidator {
  private Field field;

  public ValidateUUID(Field field) {
    this.field = field;
  }

  @Override
  public Exception validate() {
    try {
      UUID.fromString(field.getValue());
      return null; // UUID is valid
    } catch (IllegalArgumentException e) {
      return new InvalidUUIDError(field.getName());
    }
  }
}
