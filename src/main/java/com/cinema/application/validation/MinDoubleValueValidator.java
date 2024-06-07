package com.cinema.application.validation;

import com.cinema.application.errors.MinDoubleValueError;

public class MinDoubleValueValidator implements IValidator {
  private Field field;
  private double minValue;

  public MinDoubleValueValidator(Field field, double minValue) {
    this.field = field;
    this.minValue = minValue;
  }

  @Override
  public Exception validate() {
    double value = Double.parseDouble(this.field.getValue());

    if(value < this.minValue) {
      return new MinDoubleValueError(this.field.getName(), this.minValue);
    }

    return null;
  }
}
