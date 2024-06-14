package com.cinema.application.controllers.sales;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.SaleDTO;
import com.cinema.application.dtos.sales.SaleItemDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.usecases.sale.ListSalesUseCase;

/**
 * Controller class for listing sales.
 */
public class ListSalesController extends Controller<Object> {
  private ListSalesUseCase listSalesUseCase;

  public ListSalesController(ListSalesUseCase listSalesUseCase) {
    this.listSalesUseCase = listSalesUseCase;
  }

  /**
   * Performs the action of listing sales.
   *
   * @param object The object parameter
   * @return A Response object containing the list of sales.
   */
  public Response<?> perform(Object object) {
    try {
      List<Sale> sales = this.listSalesUseCase.execute();

      List<SaleDTO> salesDTO = sales.stream().map(sale -> {
        List<SaleItemDTO> productItems = List.of(sale.getProductSales().stream().map(productSale -> {
          return new SaleItemDTO(productSale.getProduct().getID(), productSale.getProduct().getName(),
              productSale.getProduct().getPrice());
        }).toArray(SaleItemDTO[]::new));

        List<SaleItemDTO> ticketItems = List.of(sale.getTicketSales().stream().map(ticketSale -> {
          return new SaleItemDTO(ticketSale.getTicket().getID(),
              ticketSale.getTicket().getMovieSession().getMovie().getTitle(),
              ticketSale.getTicket().getPrice());
        }).toArray(SaleItemDTO[]::new));

        List<SaleItemDTO> items = List.of(productItems, ticketItems).stream().flatMap(List::stream).toList();

        return new SaleDTO(sale.getID(), items,
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(sale.getSaleDate()), sale.getPerson().getCPF(),
            sale.getTotalPrice(), sale.getSalesCounter().getType().toString());
      }).toList();

      return ResponseFactory.ok(salesDTO);
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of IValidator objects representing the validators
   */
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
