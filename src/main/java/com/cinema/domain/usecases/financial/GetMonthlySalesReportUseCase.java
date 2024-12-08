package com.cinema.domain.usecases.financial;

import java.util.List;

import com.cinema.domain.contracts.repositories.financial.IGetMonthlySalesReportRepository;
import com.cinema.domain.entities.financial.MonthlySalesReport;

public class GetMonthlySalesReportUseCase {
  private IGetMonthlySalesReportRepository getMonthlySalesReportRepository;

  public GetMonthlySalesReportUseCase(IGetMonthlySalesReportRepository getMonthlySalesReportRepository) {
    this.getMonthlySalesReportRepository = getMonthlySalesReportRepository;
  }

  public List<MonthlySalesReport> execute() {
    return this.getMonthlySalesReportRepository.getMonthlySalesReport();
  }
}
