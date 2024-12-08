package com.cinema.infra.db.postgres.helpers.entities.sales;

import java.util.ArrayList;
import java.util.List;

import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgProductCart;
import com.cinema.infra.db.postgres.entities.sale.PgTicketCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.PersonConverter;

public class CartConverter implements IEntityConverter<PgCart, Cart> {
  private PersonConverter personConverter = new PersonConverter();
  private TicketCartConverter ticketConverter = new TicketCartConverter();
  private ProductCartConverter productConverter = new ProductCartConverter();

  @Override
  public Cart convert(PgCart source) {
    List<TicketCart> tickets = new ArrayList<>();

    if (source.getTickets() == null) {
      source.setTickets(new ArrayList<>());
    }

    source.getTickets().stream().forEach(pgTicket -> {
      TicketCart ticket = ticketConverter.convert(pgTicket);

      tickets.add(ticket);
    });

    List<ProductCart> products = new ArrayList<>();

    if (source.getProducts() == null) {
      source.setProducts(new ArrayList<>());
    }

    source.getProducts().stream().forEach(pgProduct -> {
      ProductCart product = productConverter.convert(pgProduct);

      products.add(product);
    });

    return new Cart(source.getID(), tickets, products, personConverter.convert(source.getPerson()));
  }

  @Override
  public PgCart pgConverter(Cart target) {
    List<PgTicketCart> pgTickets = new ArrayList<>();

    if (target.getTickets() == null) {
      target.setTickets(new ArrayList<>());
    }

    target.getTickets().stream().forEach(ticket -> {
      pgTickets.add(ticketConverter.pgConverter(ticket));
    });

    List<PgProductCart> pgProducts = new ArrayList<>();

    if (target.getProducts() == null) {
      target.setProducts(new ArrayList<>());
    }

    target.getProducts().stream().forEach(product -> {
      pgProducts.add(productConverter.pgConverter(product));
    });

    return new PgCart(target.getID(), pgTickets, pgProducts, personConverter.pgConverter(target.getPerson()));
  }
}
