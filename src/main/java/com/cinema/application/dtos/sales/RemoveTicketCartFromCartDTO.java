package com.cinema.application.dtos.sales;

import java.util.UUID;

public class RemoveTicketCartFromCartDTO {
  private UUID ID;

  public RemoveTicketCartFromCartDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return ID;
  }
}
