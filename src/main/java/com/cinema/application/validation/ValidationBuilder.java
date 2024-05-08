package com.cinema.application.validation;

import java.util.ArrayList;

public class ValidationBuilder {
  private ArrayList<IValidator> validators;

  public ValidationBuilder() {
    this.validators = new ArrayList<IValidator>();
  }

  public static ValidationBuilder of() {
    return new ValidationBuilder();
  }

  public ArrayList<IValidator> build() {
    return this.validators;
  }

  public ValidationBuilder required(ArrayList<Field> fields) {
    this.validators.add(new RequiredStringValidator(fields));

    return this;
  }
  
  public ValidationBuilder validateCPF(Field field) {
    this.validators.add(new ValidateCPF(field));

    return this;
  }

  public ValidationBuilder compareFields(Field field, Field fieldToCompare) {
    this.validators.add(new CompareFields(field, fieldToCompare));

    return this;
  }

  public ValidationBuilder minimumSize(Field field, int minimumSize) {
    this.validators.add(new MinimumSizeValidator(field, minimumSize));

    return this;
  }
}
