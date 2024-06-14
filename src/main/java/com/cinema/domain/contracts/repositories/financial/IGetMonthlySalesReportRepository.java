package com.cinema.domain.contracts.repositories.financial;

import java.util.List;

import com.cinema.domain.entities.financial.MonthlySalesReport;

public interface IGetMonthlySalesReportRepository {
  List<MonthlySalesReport> getMonthlySalesReport();
}
