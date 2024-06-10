package com.cinema.application.dtos.sales;

import java.util.UUID;

public class RemoveTicketFromCartDTO {
  private UUID ID;

  public RemoveTicketFromCartDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return ID;
  }
}
