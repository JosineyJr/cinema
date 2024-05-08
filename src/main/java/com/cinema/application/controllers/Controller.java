package com.cinema.application.controllers;

import java.util.ArrayList;

import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationComposite;

public abstract class Controller {
  public abstract Response perform(Object object);

  public abstract ArrayList<IValidator> buildValidators(Object object);

  public ArrayList<IValidator> buildValidators() {
    return new ArrayList<IValidator>();
  }

  public Response handle(Object object){
    Exception validateError = this.validate(object);

    if (validateError != null) {
      return ResponseFactory.badRequest(validateError);
    }

    try {
      return this.perform(object);
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  private Exception validate(Object object){
    ArrayList<IValidator> validators = this.buildValidators(object);

    return new ValidationComposite(validators).validate();
  }
}
