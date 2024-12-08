package com.cinema.application.dtos.sales;

import java.util.UUID;

import com.cinema.domain.enums.sale.SalesCounterType;

public class SalesCounterDTO {
  private UUID ID;

  private SalesCounterType type;

  private boolean isAvailable;

  public SalesCounterDTO(UUID ID, SalesCounterType type, boolean isAvailable) {
    this.ID = ID;
    this.type = type;
    this.isAvailable = isAvailable;
  }

  public UUID getCompleteID() {
    return this.ID;
  }

  public String getID() {
    return this.ID.toString().split("-")[0];
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public SalesCounterType getType() {
    return this.type;
  }

  public void setType(SalesCounterType type) {
    this.type = type;
  }

  public boolean isIsAvailable() {
    return this.isAvailable;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  public void setIsAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

}
