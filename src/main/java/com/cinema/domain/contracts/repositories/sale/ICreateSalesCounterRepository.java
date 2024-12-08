package com.cinema.domain.contracts.repositories.sale;

import com.cinema.domain.entities.sale.SalesCounter;

public interface ICreateSalesCounterRepository {
  public SalesCounter createSalesCounter(SalesCounter salesCounter);
}
