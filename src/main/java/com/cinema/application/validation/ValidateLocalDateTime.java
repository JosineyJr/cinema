package com.cinema.application.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.cinema.application.errors.ValidateLocalDateTimeError;

public class ValidateLocalDateTime implements IValidator {
  private Field field;

  public ValidateLocalDateTime(Field field) {
    this.field = field;
  }

  @Override
  public Exception validate() {
    String dateTime = this.field.getValue();

    try {
      LocalDateTime.parse(dateTime);
    } catch (DateTimeParseException e) {
      return new ValidateLocalDateTimeError(this.field.getName());
    }
    return null;
  }
}
