package com.cinema.application.controllers.sales;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.SalesCounterDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.domain.usecases.sale.CreateOrListSalesCounterUseCase;

public class CreateOrListSalesCounterController extends Controller<Object> {
  private CreateOrListSalesCounterUseCase createOrListSalesCounterUseCase;

  public CreateOrListSalesCounterController(CreateOrListSalesCounterUseCase createOrListSalesCounterUseCase) {
    this.createOrListSalesCounterUseCase = createOrListSalesCounterUseCase;
  }

  @Override
  public Response<?> perform(Object object) {
    try {
      List<SalesCounter> response = this.createOrListSalesCounterUseCase.execute();

      int salesCounterSize = response.size();

      SalesCounterDTO[] salesCounterDTOs = new SalesCounterDTO[salesCounterSize];

      for (int i = 0; i < salesCounterSize; i++) {
        salesCounterDTOs[i] = new SalesCounterDTO(response.get(i).getID(), response.get(i).getType(),
            response.get(i).isAvailable());
      }

      return ResponseFactory.ok(salesCounterDTOs);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
