package com.cinema.application.validation;

import java.util.ArrayList;

public class ValidationComposite implements IValidator {
  private ArrayList<IValidator> validators;

  public ValidationComposite(ArrayList<IValidator> validators) {
    this.validators = validators;
  }

  @Override
  public Exception validate() {
    for (IValidator validator : validators) {
      Exception error = validator.validate();

      if (error != null) {
        return error;
      }
    }

    return null;
  }
}
