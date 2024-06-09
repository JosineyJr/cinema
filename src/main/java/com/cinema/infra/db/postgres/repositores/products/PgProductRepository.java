package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIdRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.contracts.repositories.products.IListProductsRepository;
import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.repositores.PgRepository;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PgProductRepository
    extends PgRepository
    implements
    ICreateProductRepository,
    IFindProductByNameRepository,
    IListProductsRepository,
    IDeleteProductRepository, IFindProductByIdRepository {

  public UUID create(Product product) {
    PgProduct pgProduct = new PgProduct(
        product.getName(),
        product.getPrice());
    this.session.persist(pgProduct);

    return pgProduct.getID();
  }

  public Product findByName(String name) {
    try {
      PgProduct pgProduct = this.session.createQuery(
          "FROM product p WHERE p.name = :name",
          PgProduct.class)
          .setParameter("name", name)
          .getSingleResult();

      return new Product(
          pgProduct.getID(),
          pgProduct.getName(),
          pgProduct.getPrice());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Product> listProducts() {
    return this.session.createQuery("FROM product p", PgProduct.class)
        .getResultList()
        .stream()
        .map(
            pgProduct -> new Product(
                pgProduct.getID(),
                pgProduct.getName(),
                pgProduct.getPrice()))
        .collect(Collectors.toList());
  }

  public void deleteProduct(UUID id) {
    PgProduct pgProduct = this.session.find(PgProduct.class, id);
    this.session.remove(pgProduct);
  }

  public Product findById(UUID id) {
    PgProduct pgProduct = this.session.find(PgProduct.class, id);
    return new Product(
        pgProduct.getID(),
        pgProduct.getName(),
        pgProduct.getPrice());
  } 
}
