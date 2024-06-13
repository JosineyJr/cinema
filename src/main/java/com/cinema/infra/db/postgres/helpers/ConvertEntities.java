package com.cinema.infra.db.postgres.helpers;

import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.domain.entities.sale.ProductSale;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.domain.entities.sale.TicketSale;
import com.cinema.domain.entities.users.Admin;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Employee;
import com.cinema.domain.entities.users.Person;
import com.cinema.infra.db.postgres.entities.movies.PgCinemaHall;
import com.cinema.infra.db.postgres.entities.movies.PgGenre;
import com.cinema.infra.db.postgres.entities.movies.PgMovie;
import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgProductCart;
import com.cinema.infra.db.postgres.entities.sale.PgProductSale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgTicketCart;
import com.cinema.infra.db.postgres.entities.sale.PgTicketSale;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.entities.users.PgClient;
import com.cinema.infra.db.postgres.entities.users.PgEmployee;
import com.cinema.infra.db.postgres.entities.users.PgPerson;
import com.cinema.infra.db.postgres.helpers.entities.movies.CinemaHallConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.GenreConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.MovieConverter;
import com.cinema.infra.db.postgres.helpers.entities.movies.MovieSessionConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.InventoryConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.ProductConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.TicketConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.CartConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.ProductCartConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.ProductSaleConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.SaleConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.TicketCartConverter;
import com.cinema.infra.db.postgres.helpers.entities.sales.TicketSaleConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.AdminConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.ClientConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.EmployeeConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.PersonConverter;

public class ConvertEntities {

  public static Admin convertAdmin(PgAdmin admin) {
    return new AdminConverter().convert(admin);
  }

  public static PgAdmin pgConvertAdmin(Admin admin) {
    return new AdminConverter().pgConverter(admin);
  }

  public static Employee convertEmployee(PgEmployee employee) {
    return new EmployeeConverter().convert(employee);
  }

  public static PgEmployee pgConvertEmployee(Employee employee) {
    return new EmployeeConverter().pgConverter(employee);
  }

  public static Client convertClient(PgClient client) {
    return new ClientConverter().convert(client);
  }

  public static PgClient pgConvertClient(Client client) {
    return new ClientConverter().pgConverter(client);
  }

  public static Person convertPerson(PgPerson person) {
    return new PersonConverter().convert(person);
  }

  public static PgPerson pgConvertPerson(Person person) {
    return new PersonConverter().pgConverter(person);
  }

  public static Genre convertGenre(PgGenre genre) {
    return new GenreConverter().convert(genre);
  }

  public static PgGenre pgConvertGenre(Genre genre) {
    return new GenreConverter().pgConverter(genre);
  }

  public static CinemaHall convertCinemaHall(PgCinemaHall cinemaHall) {
    return new CinemaHallConverter().convert(cinemaHall);
  }

  public static PgCinemaHall pgConvertCinemaHall(CinemaHall cinemaHall) {
    return new CinemaHallConverter().pgConverter(cinemaHall);
  }

  public static Movie convertMovie(PgMovie movie) {
    return new MovieConverter().convert(movie);
  }

  public static PgMovie pgConvertMovie(Movie movie) {
    return new MovieConverter().pgConverter(movie);
  }

  public static MovieSession convertMovieSession(PgMovieSession movieSession) {
    return new MovieSessionConverter().convert(movieSession);
  }

  public static PgMovieSession pgConvertMovieSession(MovieSession movieSession) {
    return new MovieSessionConverter().pgConverter(movieSession);
  }

  public static Ticket convertTicket(PgTicket ticket) {
    return new TicketConverter().convert(ticket);
  }

  public static PgTicket pgConvertTicket(Ticket ticket) {
    return new TicketConverter().pgConverter(ticket);
  }

  public static TicketCart convertTicketCart(PgTicketCart ticketCart) {
    return new TicketCartConverter().convert(ticketCart);
  }

  public static PgTicketCart pgConvertTicketCart(TicketCart ticketCart) {
    return new TicketCartConverter().pgConverter(ticketCart);
  }

  public static Product convertProduct(PgProduct product) {
    return new ProductConverter().convert(product);
  }

  public static PgProduct pgConvertProduct(Product product) {
    return new ProductConverter().pgConverter(product);
  }

  public static ProductCart convertProductCart(PgProductCart productCart) {
    return new ProductCartConverter().convert(productCart);
  }

  public static PgProductCart pgConvertProductCart(ProductCart productCart) {
    return new ProductCartConverter().pgConverter(productCart);
  }

  public static Inventory convertInventory(PgInventory inventory) {
    return new InventoryConverter().convert(inventory);
  }

  public static PgInventory pgConvertInventory(Inventory inventory) {
    return new InventoryConverter().pgConverter(inventory);
  }

  public static Cart convertCart(PgCart cart) {
    return new CartConverter().convert(cart);
  }

  public static PgCart pgConvertCart(Cart cart) {
    return new CartConverter().pgConverter(cart);
  }

  public static Sale convertSale(PgSale sale) {
    return new SaleConverter().convert(sale);
  }

  public static PgSale pgConvertSale(Sale sale) {
    return new SaleConverter().pgConverter(sale);
  }

  public static TicketSale convertTicketSale(PgTicketSale ticketSale) {
    return new TicketSaleConverter().convert(ticketSale);
  }

  public static PgTicketSale pgConvertTicketSale(TicketSale ticketSale) {
    return new TicketSaleConverter().pgConverter(ticketSale);
  }

  public static ProductSale convertProductSale(PgProductSale productSale) {
    return new ProductSaleConverter().convert(productSale);
  }

  public static PgProductSale pgConvertProductSale(ProductSale productSale) {
    return new ProductSaleConverter().pgConverter(productSale);
  }
}
