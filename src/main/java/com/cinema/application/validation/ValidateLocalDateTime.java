package com.cinema.application.validation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

      LocalTime.parse(dateTime, formatter);

    } catch (DateTimeParseException e) {
      return new ValidateLocalDateTimeError(this.field.getName());
    }
    return null;
  }
}
