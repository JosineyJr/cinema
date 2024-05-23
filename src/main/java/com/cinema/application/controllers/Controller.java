package com.cinema.application.controllers;

import java.util.ArrayList;

import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationComposite;

public abstract class Controller<T> {
  public abstract Response<?> perform(T object);

  public abstract ArrayList<IValidator> buildValidators(T object);

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

  private Exception validate(T object) {
    ArrayList<IValidator> validators = this.buildValidators(object);

    return new ValidationComposite(validators).validate();
  }
}
