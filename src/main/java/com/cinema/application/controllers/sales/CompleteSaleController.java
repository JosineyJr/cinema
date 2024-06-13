package com.cinema.application.controllers.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.CompleteSaleDTO;
import com.cinema.application.dtos.sales.ProductsCartDTO;
import com.cinema.application.dtos.sales.TicketsCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.sale.AllProductsSoldError;
import com.cinema.domain.errors.sale.AllTicketsSoldError;
import com.cinema.domain.errors.sale.MovieSessionAlreadyShown;
import com.cinema.domain.errors.sale.ProductCartNotFoundError;
import com.cinema.domain.errors.sale.TicketCartNotFoundError;
import com.cinema.domain.errors.users.PersonNotFoundError;
import com.cinema.domain.usecases.sale.CompleteSaleUseCase;

public class CompleteSaleController extends Controller<CompleteSaleDTO> {
  CompleteSaleUseCase completeSaleUseCase;

  public CompleteSaleController(CompleteSaleUseCase completeSaleUseCase) {
    this.completeSaleUseCase = completeSaleUseCase;
  }

  @Override
  public Response<?> perform(CompleteSaleDTO object) {
    try {
      List<UUID> ticketsCartIDs = new ArrayList<>();

      for (TicketsCartDTO ticketCartID : object.getTicketsCart()) {
        ticketsCartIDs.add(ticketCartID.getID());
      }

      List<UUID> productsCartIDs = new ArrayList<>();

      for (ProductsCartDTO productCartID : object.getProductsCart()) {
        productsCartIDs.add(productCartID.getID());
      }

      this.completeSaleUseCase.execute(productsCartIDs, ticketsCartIDs, object.getPersonID());

      return ResponseFactory.noContent();
    } catch (PersonNotFoundError | TicketCartNotFoundError | AllTicketsSoldError | ProductCartNotFoundError
        | AllProductsSoldError | MovieSessionAlreadyShown e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CompleteSaleDTO object) {
    Field personID = new Field(object.getPersonID().toString(), "ID da pessoa");

    String itens = String.valueOf(object.getTicketsCart().size() + object.getProductsCart().size());

    Field totalItens = new Field(itens, "Itens no carrinho");

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().validateUUID(personID).minValue(totalItens, 1).build());

    return validators;
  }

}
