package com.cinema.application.validation;

import java.util.ArrayList;

import com.cinema.application.errors.RequiredFieldError;

public class RequiredStringValidator implements IValidator {
  private ArrayList<Field> fields;

  public RequiredStringValidator(ArrayList<Field> fields) {
    this.fields = fields;
  }

  @Override
  public Exception validate() {
    for (Field field : this.fields) {
      if (field.getValue() == null || field.getValue().isEmpty()) {
        return new RequiredFieldError(field.getName());
      }
    }

    return null;
  }
}
