package com.cinema.application.dtos.sales;

import java.util.UUID;

public class RemoveProductFromCartDTO {
  private UUID ID;

  public RemoveProductFromCartDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return ID;
  }
}
