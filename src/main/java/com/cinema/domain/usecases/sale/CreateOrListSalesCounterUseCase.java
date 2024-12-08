package com.cinema.domain.usecases.sale;

import java.util.ArrayList;
import java.util.List;

import com.cinema.domain.contracts.repositories.sale.ICreateSalesCounterRepository;
import com.cinema.domain.contracts.repositories.sale.IListSalesCounterRepository;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.domain.enums.sale.SalesCounterType;

public class CreateOrListSalesCounterUseCase {
  private IListSalesCounterRepository listSalesCounterRepository;
  private ICreateSalesCounterRepository createSalesCounterRepository;

  public CreateOrListSalesCounterUseCase(IListSalesCounterRepository listSalesCounterRepository,
      ICreateSalesCounterRepository createSalesCounterRepository) {
    this.listSalesCounterRepository = listSalesCounterRepository;
    this.createSalesCounterRepository = createSalesCounterRepository;
  }

  public List<SalesCounter> execute() {
    SalesCounter[] salesCounters = new SalesCounter[5];

    List<SalesCounter> salesCounterList = listSalesCounterRepository.listSalesCounter();

    if (salesCounterList.size() == 0) {
      for (int i = 0; i < 5; i++) {
        if (i == 0) {
          SalesCounter manualSalesCounter = new SalesCounter(SalesCounterType.MANUAL, true);

          SalesCounter salesCounter = createSalesCounterRepository.createSalesCounter(manualSalesCounter);
          salesCounters[i] = salesCounter;
        } else {
          SalesCounter automaticSalesCounter = new SalesCounter(SalesCounterType.AUTOMATIC, true);

          SalesCounter salesCounter = createSalesCounterRepository.createSalesCounter(automaticSalesCounter);
          salesCounters[i] = salesCounter;
        }

      }
    } else {
      for (int i = 0; i < 5; i++) {
        SalesCounter salesCounter = salesCounterList.get(i);
        salesCounters[i] = salesCounter;
      }
    }

    List<SalesCounter> availableSalesCounters = new ArrayList<>();

    for (SalesCounter salesCounter : salesCounters) {
      if (salesCounter.isAvailable()) {
        availableSalesCounters.add(salesCounter);
      }
    }

    return availableSalesCounters;
  }
}
