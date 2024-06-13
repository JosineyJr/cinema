package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

public interface ICleanCartByPersonIDRepository {
  public void cleanCart(UUID personID);
}
