package com.cinema.application.validation;

import com.cinema.application.errors.CompareFieldsError;

public class CompareFields implements IValidator {
  private Field field;
  private Field fieldToCompare;

  public CompareFields(Field field, Field fieldToCompare) {
    this.field = field;
    this.fieldToCompare = fieldToCompare;
  }

  @Override
  public Exception validate() {
    if(!this.field.getValue().equals(this.fieldToCompare.getValue())) {
      return new CompareFieldsError(this.field.getName(), this.fieldToCompare.getName());
    }

    return null;
  }
  
}
