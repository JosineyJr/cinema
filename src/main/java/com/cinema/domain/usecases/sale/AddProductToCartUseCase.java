package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IFindInventoryByProductInfosIDRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductInfosByIdRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateCartRepository;
import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.products.ProductInfosNotFoundError;
import com.cinema.domain.errors.sale.AllProductsSoldError;

public class AddProductToCartUseCase {
  IFindProductInfosByIdRepository findProductInfosByIdRepository;
  IFindCartByPersonIDRepository findCartByPersonID;
  IFindPersonByIDRepository findPersonByIDRepository;
  ICreateCartRepository createCartRepository;
  IUpdateCartRepository updateCartRepository;
  IFindInventoryByProductInfosIDRepository findInventoryByProductInfosIDRepository;

  public AddProductToCartUseCase(
      IFindProductInfosByIdRepository findProductInfosByIdRepository,
      IFindCartByPersonIDRepository findCartByPersonID,
      IFindPersonByIDRepository findPersonByIDRepository,
      ICreateCartRepository createCartRepository,
      IUpdateCartRepository updateCartRepository,
      IFindInventoryByProductInfosIDRepository findInventoryByProductInfosIDRepository) {
    this.findProductInfosByIdRepository = findProductInfosByIdRepository;
    this.findCartByPersonID = findCartByPersonID;
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.createCartRepository = createCartRepository;
    this.updateCartRepository = updateCartRepository;
    this.findInventoryByProductInfosIDRepository = findInventoryByProductInfosIDRepository;
  }

  public void execute(UUID productInfoID, UUID personID) throws ProductInfosNotFoundError, AllProductsSoldError {
    ProductInfos productInfos = this.findProductInfosByIdRepository.findById(productInfoID);

    if (productInfos == null) {
      throw new ProductInfosNotFoundError();
    }

    Cart cart = this.findCartByPersonID.findCartByPersonID(personID);

    if (cart == null) {
      Person person = this.findPersonByIDRepository.findPersonByID(personID);
      cart = new Cart(person);

      cart = this.createCartRepository.createCart(cart);
    }

    Inventory inventory = this.findInventoryByProductInfosIDRepository.findInventoryByProductInfosID(productInfoID);

    Product product = new Product(productInfos, cart);

    cart.addProduct(product, inventory.getQuantity());

    if (!cart.addProduct(product, inventory.getQuantity())) {
      throw new AllProductsSoldError();
    }

    this.updateCartRepository.updateCart(cart);
  }
}
