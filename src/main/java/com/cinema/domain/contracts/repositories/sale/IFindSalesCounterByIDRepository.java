package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.SalesCounter;

public interface IFindSalesCounterByIDRepository {
  public SalesCounter findByID(UUID ID);
}
