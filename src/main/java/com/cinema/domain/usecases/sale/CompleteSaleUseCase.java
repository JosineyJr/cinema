package com.cinema.domain.usecases.sale;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDateTime;

import com.cinema.domain.contracts.repositories.products.IFindInventoryByProductIDRepository;
import com.cinema.domain.contracts.repositories.products.IRemoveProductItemFromInventory;
import com.cinema.domain.contracts.repositories.sale.ICleanCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateProductSaleRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateSaleRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateTicketSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IFindProductCartByIDRepository;
import com.cinema.domain.contracts.repositories.sale.IFindProductsSaleByProductID;
import com.cinema.domain.contracts.repositories.sale.IFindTicketCartByIDRepository;
import com.cinema.domain.contracts.repositories.sale.IFindTicketsSaleByMovieSessionID;
import com.cinema.domain.contracts.repositories.sale.IUpdateSaleRepository;
import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.domain.entities.sale.ProductSale;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.domain.entities.sale.TicketSale;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.sale.AllProductsSoldError;
import com.cinema.domain.errors.sale.AllTicketsSoldError;
import com.cinema.domain.errors.sale.MovieSessionAlreadyShown;
import com.cinema.domain.errors.sale.ProductCartNotFoundError;
import com.cinema.domain.errors.sale.TicketCartNotFoundError;
import com.cinema.domain.errors.users.PersonNotFoundError;
import com.cinema.domain.helpers.TimeIsAfter;

public class CompleteSaleUseCase {
  IFindTicketCartByIDRepository findTicketCartByIDRepository;
  IFindProductCartByIDRepository findProductCartByIDRepository;
  IFindPersonByIDRepository findPersonByIDRepository;
  ICreateSaleRepository createSaleRepository;
  IUpdateSaleRepository updateSaleRepository;
  IFindInventoryByProductIDRepository findInventoryByProductIDRepository;
  IFindTicketsSaleByMovieSessionID findTicketsSaleByMovieSessionID;
  IFindProductsSaleByProductID findProductsSaleByProductID;
  IRemoveProductItemFromInventory removeProductItemFromInventory;
  ICreateTicketSaleRepository createTicketSaleRepository;
  ICreateProductSaleRepository createProductSaleRepository;
  ICleanCartByPersonIDRepository cleanCartByPersonIDRepository;

  public CompleteSaleUseCase(IFindTicketCartByIDRepository findTicketCartByIDRepository,
      IFindProductCartByIDRepository findProductCartByIDRepository, IFindPersonByIDRepository findPersonByIDRepository,
      ICreateSaleRepository createSaleRepository, IUpdateSaleRepository updateSaleRepository,
      IFindInventoryByProductIDRepository findInventoryByProductIDRepository,
      IFindTicketsSaleByMovieSessionID findTicketsSaleByMovieSessionID,
      IFindProductsSaleByProductID findProductsSaleByProductID,
      IRemoveProductItemFromInventory removeProductItemFromInventory,
      ICreateTicketSaleRepository createTicketSaleRepository, ICreateProductSaleRepository createProductSaleRepository,
      ICleanCartByPersonIDRepository cleanCartByPersonIDRepository) {
    this.findTicketCartByIDRepository = findTicketCartByIDRepository;
    this.findProductCartByIDRepository = findProductCartByIDRepository;
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.createSaleRepository = createSaleRepository;
    this.updateSaleRepository = updateSaleRepository;
    this.findInventoryByProductIDRepository = findInventoryByProductIDRepository;
    this.findTicketsSaleByMovieSessionID = findTicketsSaleByMovieSessionID;
    this.findProductsSaleByProductID = findProductsSaleByProductID;
    this.removeProductItemFromInventory = removeProductItemFromInventory;
    this.createTicketSaleRepository = createTicketSaleRepository;
    this.createProductSaleRepository = createProductSaleRepository;
    this.cleanCartByPersonIDRepository = cleanCartByPersonIDRepository;
  }

  public void execute(List<UUID> productsCartIDs, List<UUID> ticketsCartIDs, UUID personID)
      throws PersonNotFoundError, TicketCartNotFoundError, ProductCartNotFoundError, AllTicketsSoldError,
      AllProductsSoldError, MovieSessionAlreadyShown {

    Person person = this.findPersonByIDRepository.findPersonByID(personID);

    if (person == null) {
      throw new PersonNotFoundError();
    }

    Sale sale = new Sale(person);

    UUID saleID = this.createSaleRepository.createSale(sale);

    sale.setID(saleID);

    List<TicketSale> tickets = new ArrayList<TicketSale>();

    for (UUID ticketCartID : ticketsCartIDs) {
      TicketCart ticketCart = this.findTicketCartByIDRepository.findByID(ticketCartID);

      if (ticketCart == null) {
        throw new TicketCartNotFoundError();
      }

      LocalDateTime isValidDate = ticketCart.getTicket().getMovieSession().getStartDate();

      if (!TimeIsAfter.validate(isValidDate)) {
        String startDate = ticketCart.getTicket().getMovieSession().getStartDate().toString();

        throw new MovieSessionAlreadyShown(startDate);
      }

      TicketSale ticketSale = new TicketSale(ticketCart.getTicket(), sale, ticketCart.getPrice());

      UUID movieSessionID = ticketCart.getTicket().getMovieSession().getID();

      List<TicketSale> ticketSales = this.findTicketsSaleByMovieSessionID
          .findTicketsSaleByMovieSessionID(movieSessionID);

      boolean isTicketsAvailable = ticketSales.size() < ticketCart.getTicket().getMovieSession().getCinemaHall()
          .getCapacity();

      if (!isTicketsAvailable) {
        int availableTickets = ticketCart.getTicket().getMovieSession().getCinemaHall().getCapacity()
            - ticketSales.size();

        String startDate = ticketCart.getTicket().getMovieSession().getStartDate().toString();

        String cinemaHallName = ticketCart.getTicket().getMovieSession().getCinemaHall().getName();

        throw new AllTicketsSoldError(availableTickets, startDate, cinemaHallName);
      }

      UUID ticketSaleID = this.createTicketSaleRepository.create(ticketSale);

      ticketSale.setID(ticketSaleID);

      tickets.add(ticketSale);
    }

    List<ProductSale> products = new ArrayList<ProductSale>();

    for (UUID productCartID : productsCartIDs) {
      ProductCart productCart = this.findProductCartByIDRepository.findProductByID(productCartID);

      if (productCart == null) {
        throw new ProductCartNotFoundError();
      }

      ProductSale productSale = new ProductSale(productCart.getProduct(), sale, productCart.getPrice());

      Inventory inventory = this.findInventoryByProductIDRepository
          .findInventoryByProductID(productCart.getProduct().getID());

      List<ProductSale> productSales = this.findProductsSaleByProductID
          .findProductsSaleByProductID(productCart.getProduct().getID());

      boolean isProductAvailable = productSales.size() < inventory.getQuantity();

      if (!isProductAvailable) {
        throw new AllProductsSoldError(productSale.getProduct().getName());
      }

      UUID productSaleID = this.createProductSaleRepository.create(productSale);

      productSale.setID(productSaleID);

      products.add(productSale);

      this.removeProductItemFromInventory.removeProductItemFromInventory(productCart.getProduct().getID(), 1);
    }

    sale.setTicketSales(tickets);
    sale.setProductSales(products);

    sale.calculateTotalPrice();

    sale.setSaleDate(LocalDateTime.now());

    this.updateSaleRepository.updateSale(sale);

    this.cleanCartByPersonIDRepository.cleanCart(personID);
  }
}
