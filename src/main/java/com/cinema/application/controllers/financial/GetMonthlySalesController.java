package com.cinema.application.controllers.financial;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.usecases.financial.GetMonthlySalesReportUseCase;

public class GetMonthlySalesController extends Controller<Object> {
  private GetMonthlySalesReportUseCase getMonthlySalesReportUseCase;

  public GetMonthlySalesController(GetMonthlySalesReportUseCase getMonthlySalesReportUseCase) {
    this.getMonthlySalesReportUseCase = getMonthlySalesReportUseCase;
  }

  /**
   * Performs the action of retrieving the monthly sales report.
   *
   * @param object The object parameter (not used in this implementation).
   * @return A Response object containing the monthly sales report.
   */
  public Response<?> perform(Object object) {
    try {
      return ResponseFactory.ok(this.getMonthlySalesReportUseCase.execute());
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of IValidator objects representing the validators
   */
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
