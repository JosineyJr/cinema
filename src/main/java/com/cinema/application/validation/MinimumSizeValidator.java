package com.cinema.application.validation;

import com.cinema.application.errors.MinimumSizeError;

public class MinimumSizeValidator implements IValidator {
  private Field field;
  private int minimumSize;

  public MinimumSizeValidator(Field field, int minimumSize) {
    this.field = field;
    this.minimumSize = minimumSize;
  }

  @Override
  public Exception validate() {
    if(this.field.getValue().length() < this.minimumSize) {
      return new MinimumSizeError(this.field.getName(), this.minimumSize);
    }

    return null;
  }

}
