package com.cinema.domain.contracts.repositories.sale;

import java.util.List;

import com.cinema.domain.entities.sale.SalesCounter;

public interface IListSalesCounterRepository {
  public List<SalesCounter> listSalesCounter();
}
