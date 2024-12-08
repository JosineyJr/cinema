package com.cinema.application.controllers.sales;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.ChangeAvailableSalesCounterDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.sale.SalesCounterNotFoundError;
import com.cinema.domain.usecases.sale.ChangeAvailableSalesCounterUseCase;

public class ChangeAvailableSalesCounterController extends Controller<ChangeAvailableSalesCounterDTO> {
  private ChangeAvailableSalesCounterUseCase changeAvailableSalesCounterUseCase;

  public ChangeAvailableSalesCounterController(ChangeAvailableSalesCounterUseCase changeAvailableSalesCounterUseCase) {
    this.changeAvailableSalesCounterUseCase = changeAvailableSalesCounterUseCase;
  }

  @Override
  public Response<?> perform(ChangeAvailableSalesCounterDTO object) {
    try {
      this.changeAvailableSalesCounterUseCase.execute(object.getID(), object.getIsAvailable());

      return ResponseFactory.noContent();
    } catch (SalesCounterNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(ChangeAvailableSalesCounterDTO object) {
    Field field = new Field(object.getID().toString(), "ID do balcão de vendas");

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().validateUUID(field).build());

    return validators;
  }

}
