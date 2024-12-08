package com.cinema.application.dtos.sales;

public class AddTicketToCartDTO {
  private String ticketID;
  private String personID;

  public AddTicketToCartDTO(String ticketID, String personID) {
    this.ticketID = ticketID;
    this.personID = personID;
  }

  public String getTicketID() {
    return this.ticketID;
  }

  public void setTicketID(String ticketID) {
    this.ticketID = ticketID;
  }

  public String getPersonID() {
    return this.personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

}
