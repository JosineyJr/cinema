package com.cinema.application.validation;

import java.util.ArrayList;

/**
 * The ValidationBuilder class is responsible for building a list of validators for field validation.
 * It provides methods to add different types of validators to the list.
 */
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

  public ValidationBuilder validateUUID(Field field) {
    this.validators.add(new ValidateUUID(field));

    return this;
  }

  public ValidationBuilder minValue(Field field, int minValue) {
    this.validators.add(new MinValueValidator(field, minValue));

    return this;
  }

  public ValidationBuilder minDoubleValue(Field field, double minDoubleValue) {
    this.validators.add(new MinDoubleValueValidator(field, minDoubleValue));

    return this;
  }

  public ValidationBuilder validateLocalDateTime(Field field) {
    this.validators.add(new ValidateLocalDateTime(field));

    return this;
  }
}
