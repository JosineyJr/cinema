package com.cinema.domain.contracts.repositories.sale;

import java.util.List;

import com.cinema.domain.entities.sale.Sale;

public interface IListSalesRepository {
  List<Sale> listSales();
}
