package com.cinema.domain.contracts.repositories.financial;

import java.util.List;

import com.cinema.domain.entities.financial.DailySalesReport;

public interface IGetMonthlySalesReportRepository {
  List<DailySalesReport> getDailySalesReport();
}
