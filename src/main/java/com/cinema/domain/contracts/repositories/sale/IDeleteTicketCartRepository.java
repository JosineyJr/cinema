package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

public interface IDeleteTicketCartRepository {
  void deleteTicketCart(UUID ticketCardID);
}
