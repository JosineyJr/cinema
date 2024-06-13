package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IFindInventoryByProductIDRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIDRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateCartRepository;
import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.products.ProductNotFoundError;
import com.cinema.domain.errors.sale.AllProductsSoldError;

public class AddProductToCartUseCase {
  IFindProductByIDRepository findProductByIdRepository;
  IFindCartByPersonIDRepository findCartByPersonID;
  IFindPersonByIDRepository findPersonByIDRepository;
  ICreateCartRepository createCartRepository;
  IUpdateCartRepository updateCartRepository;
  IFindInventoryByProductIDRepository findInventoryByproductIDRepository;

  public AddProductToCartUseCase(
      IFindProductByIDRepository findProductByIdRepository,
      IFindCartByPersonIDRepository findCartByPersonID,
      IFindPersonByIDRepository findPersonByIDRepository,
      ICreateCartRepository createCartRepository,
      IUpdateCartRepository updateCartRepository,
      IFindInventoryByProductIDRepository findInventoryByproductIDRepository) {
    this.findProductByIdRepository = findProductByIdRepository;
    this.findCartByPersonID = findCartByPersonID;
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.createCartRepository = createCartRepository;
    this.updateCartRepository = updateCartRepository;
    this.findInventoryByproductIDRepository = findInventoryByproductIDRepository;
  }

  public void execute(UUID productID, UUID personID) throws ProductNotFoundError, AllProductsSoldError {
    Product product = this.findProductByIdRepository.findById(productID);

    if (product == null) {
      throw new ProductNotFoundError();
    }

    Cart cart = this.findCartByPersonID.findCartByPersonID(personID);

    if (cart == null) {
      Person person = this.findPersonByIDRepository.findPersonByID(personID);
      cart = new Cart(person);

      cart = this.createCartRepository.createCart(cart);
    }

    Inventory inventory = this.findInventoryByproductIDRepository.findInventoryByProductID(productID);

    ProductCart productCart = new ProductCart(product, cart, product.getPrice());

    boolean isProductAdded = cart.addProduct(productCart, inventory.getQuantity());

    if (!isProductAdded) {
      throw new AllProductsSoldError();
    }

    this.updateCartRepository.updateCart(cart);
  }
}
