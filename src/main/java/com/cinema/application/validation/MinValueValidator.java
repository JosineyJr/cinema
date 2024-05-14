package com.cinema.application.validation;

import com.cinema.application.errors.MinValueError;

public class MinValueValidator implements IValidator {
  private Field field;
  private int minValue;

  public MinValueValidator(Field field, int minValue) {
    this.field = field;
    this.minValue = minValue;
  }

  @Override
  public Exception validate() {
    int value = Integer.parseInt(this.field.getValue());

    if(value < this.minValue) {
      return new MinValueError(this.field.getName(), this.minValue);
    }

    return null;
  }
}
