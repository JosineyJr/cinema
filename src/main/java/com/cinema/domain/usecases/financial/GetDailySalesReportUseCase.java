package com.cinema.domain.usecases.financial;

import java.util.List;

import com.cinema.domain.contracts.repositories.financial.IGetDailySalesReportRepository;
import com.cinema.domain.entities.financial.DailySalesReport;

public class GetDailySalesReportUseCase {
  private IGetDailySalesReportRepository getDailySalesReportRepository;

  public GetDailySalesReportUseCase(IGetDailySalesReportRepository getDailySalesReportRepository) {
    this.getDailySalesReportRepository = getDailySalesReportRepository;
  }

  /**
   * Executes the use case to retrieve the daily sales report.
   *
   * @return a list of Sale objects representing the daily sales report.
   */
  public List<DailySalesReport> execute() {
    return this.getDailySalesReportRepository.getDailySalesReport();
  }
}
