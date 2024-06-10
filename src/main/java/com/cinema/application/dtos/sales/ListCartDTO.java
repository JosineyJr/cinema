package com.cinema.application.dtos.sales;

import java.util.UUID;

public class ListCartDTO {
  private UUID personID;

  public ListCartDTO(UUID personID) {
    this.personID = personID;
  }

  public UUID getPersonID() {
    return personID;
  }
}
