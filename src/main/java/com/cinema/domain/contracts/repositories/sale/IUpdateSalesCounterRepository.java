package com.cinema.domain.contracts.repositories.sale;

import com.cinema.domain.entities.sale.SalesCounter;

public interface IUpdateSalesCounterRepository {
  public void update(SalesCounter salesCounter);
}
