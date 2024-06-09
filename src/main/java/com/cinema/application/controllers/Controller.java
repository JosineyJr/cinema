package com.cinema.application.controllers;

import java.util.ArrayList;

import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationComposite;

public abstract class Controller<T> {
  /**
   * Performs an operation on the given object.
   *
   * @param object the object on which the operation is performed
   * @return a Response object representing the result of the operation
   */
  public abstract Response<?> perform(T object);

  /**
   * Builds and returns an ArrayList of validators for the specified object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of validators for the specified object
   */
  public abstract ArrayList<IValidator> buildValidators(T object);

  /**
   * Handles the given object by validating it and performing the necessary actions.
   *
   * @param object The object to be handled.
   * @return A Response object representing the result of the handling process.
   */
  public Response<?> handle(T object) {
    Exception validateError = this.validate(object);

    if (validateError != null) {
      return ResponseFactory.badRequest(validateError);
    }

    try {
      return this.perform(object);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Validates the given object using a list of validators.
   *
   * @param object the object to be validated
   * @return an Exception object if validation fails, null otherwise
   */
  private Exception validate(T object) {
    ArrayList<IValidator> validators = this.buildValidators(object);

    return new ValidationComposite(validators).validate();
  }
}
