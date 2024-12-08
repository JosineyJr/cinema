package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.IFindSalesCounterByIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSalesCounterRepository;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.domain.errors.sale.SalesCounterNotFoundError;

public class ChangeAvailableSalesCounterUseCase {
  private IFindSalesCounterByIDRepository findSalesCounterByIDRepository;
  private IUpdateSalesCounterRepository updateSalesCounterRepository;

  public ChangeAvailableSalesCounterUseCase(IFindSalesCounterByIDRepository findSalesCounterByIDRepository,
      IUpdateSalesCounterRepository updateSalesCounterRepository) {
    this.findSalesCounterByIDRepository = findSalesCounterByIDRepository;
    this.updateSalesCounterRepository = updateSalesCounterRepository;
  }

  public void execute(UUID salesCounterID, boolean isAvailable) throws SalesCounterNotFoundError {
    SalesCounter salesCounter = findSalesCounterByIDRepository.findByID(salesCounterID);

    if (salesCounter == null) {
      throw new SalesCounterNotFoundError();
    }

    salesCounter.setAvailable(isAvailable);

    System.out.println(salesCounter.isAvailable());

    updateSalesCounterRepository.update(salesCounter);
  }
}
