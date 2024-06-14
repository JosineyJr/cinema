package com.cinema.application.controllers.financial;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.financial.DailySalesReport;
import com.cinema.domain.usecases.financial.GetDailySalesReportUseCase;

public class GetDailySalesController extends Controller<Object> {
  private GetDailySalesReportUseCase getDailySalesReportUseCase;

  public GetDailySalesController(GetDailySalesReportUseCase getDailySalesReportUseCase) {
    this.getDailySalesReportUseCase = getDailySalesReportUseCase;
  }

  /**
   * Performs the operation to get the daily sales report.
   *
   * @param object The input object (not used in this implementation).
   * @return A Response object containing the daily sales report or an error message.
   */
  public Response<?> perform(Object object) {
    try {
      List<DailySalesReport> sales = this.getDailySalesReportUseCase.execute();

      return ResponseFactory.ok(sales);
    } catch (Exception e) {
      System.out.println(e.getMessage());
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
