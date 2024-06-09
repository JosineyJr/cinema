package com.cinema.application.dtos.sales;

public class AddTicketToCartDTO {
  private String ticketInfoID;
  private String personID;

  public AddTicketToCartDTO(String ticketInfoID, String personID) {
    this.ticketInfoID = ticketInfoID;
    this.personID = personID;
  }

  public String getTicketInfoID() {
    return this.ticketInfoID;
  }

  public void setTicketInfoID(String ticketInfoID) {
    this.ticketInfoID = ticketInfoID;
  }

  public String getPersonID() {
    return this.personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

}
