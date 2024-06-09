package com.cinema.infra.db.postgres.helpers.entities.sales;

import java.util.ArrayList;
import java.util.List;

import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.ProductConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.TicketConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.PersonConverter;

public class CartConverter implements IEntityConverter<PgCart, Cart> {
  private PersonConverter personConverter = new PersonConverter();
  private TicketConverter ticketConverter = new TicketConverter();
  private ProductConverter productConverter = new ProductConverter();

  @Override
  public Cart convert(PgCart source) {
    List<Ticket> tickets = new ArrayList<>();

    source.getTickets().stream().forEach(pgTicket -> {
      Ticket ticket = ticketConverter.convert(pgTicket);

      tickets.add(ticket);
    });

    List<Product> products = new ArrayList<>();

    source.getProducts().stream().forEach(pgProduct -> {
      Product product = productConverter.convert(pgProduct);

      products.add(product);
    });

    return new Cart(source.getID(), tickets, products, personConverter.convert(source.getPerson()));
  }

  @Override
  public PgCart pgConverter(Cart target) {
    List<PgTicket> pgTickets = new ArrayList<>();

    target.getTickets().stream().forEach(ticket -> {
      pgTickets.add(ticketConverter.pgConverter(ticket));
    });

    List<PgProduct> pgProducts = new ArrayList<>();

    target.getProducts().stream().forEach(product -> {
      pgProducts.add(productConverter.pgConverter(product));
    });

    return new PgCart(target.getID(), pgTickets, pgProducts, personConverter.pgConverter(target.getPerson()));
  }
}
