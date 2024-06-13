package com.cinema.application.dtos.sales;

import java.util.UUID;

public class ChangeAvailableSalesCounterDTO {
  private UUID ID;
  private boolean isAvailable;

  public ChangeAvailableSalesCounterDTO(UUID ID, boolean isAvailable) {
    this.ID = ID;
    this.isAvailable = isAvailable;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
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
