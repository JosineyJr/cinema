package com.cinema.main.routes.sales;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.sales.AddProductToCartDTO;
import com.cinema.application.dtos.sales.AddTicketToCartDTO;
import com.cinema.application.dtos.sales.ListCartDTO;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.application.dtos.sales.RemoveTicketCartFromCartDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.sales.AddProductToCartFactory;
import com.cinema.main.factories.sales.AddTicketToCartFactory;
import com.cinema.main.factories.sales.ListPersonCartFactory;
import com.cinema.main.factories.sales.RemoveProductFromCartFactory;
import com.cinema.main.factories.sales.RemoveTicketFromCartFactory;

@RestController
public class CartRoutes {
  private final SpringAdapter<AddProductToCartDTO> addProductToCartAdapter;
  private final SpringAdapter<AddTicketToCartDTO> addTicketToCartAdapter;
  private final SpringAdapter<ListCartDTO> listCartAdapter;
  private final SpringAdapter<RemoveProductFromCartDTO> deleteProductToCartAdapter;
  private final SpringAdapter<RemoveTicketCartFromCartDTO> deleteTicketToCartAdapter;

  public CartRoutes() {
    this.addProductToCartAdapter = new SpringAdapter<>(AddProductToCartFactory.make());
    this.addTicketToCartAdapter = new SpringAdapter<>(AddTicketToCartFactory.make());
    this.listCartAdapter = new SpringAdapter<>(ListPersonCartFactory.make());
    this.deleteProductToCartAdapter = new SpringAdapter<>(RemoveProductFromCartFactory.make());
    this.deleteTicketToCartAdapter = new SpringAdapter<>(RemoveTicketFromCartFactory.make());
  }

  @PostMapping("/add-product-to-cart")
  public ResponseEntity<?> addProductToCart(@RequestBody AddProductToCartDTO addProduct) {
    return this.addProductToCartAdapter.adapt(addProduct);
  }

  @PostMapping("/add-ticket-to-cart")
  public ResponseEntity<?> postProduct(@RequestBody AddTicketToCartDTO addTicket) {
    return addTicketToCartAdapter.adapt(addTicket);
  }

  @GetMapping("/list-person-cart/{personId}")
  public ResponseEntity<?> listCart(@PathVariable UUID personId) {
    ListCartDTO cart = new ListCartDTO(personId);
    return listCartAdapter.adapt(cart);
  }

  @DeleteMapping("/delete-product-to-cart/{id}")
  public ResponseEntity<?> deleteProductToCart(@PathVariable UUID id) {
    RemoveProductFromCartDTO productCart = new RemoveProductFromCartDTO(id);
    return deleteProductToCartAdapter.adapt(productCart);
  }

  @DeleteMapping("/delete-ticket-to-cart/{id}")
  public ResponseEntity<?> deleteTicketToCart(@PathVariable UUID id) {
    RemoveTicketCartFromCartDTO ticketCart = new RemoveTicketCartFromCartDTO(id);
    return deleteTicketToCartAdapter.adapt(ticketCart);
  }
}
