package com.cinema.infra.db.postgres.repositores.products;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.repositores.PgRepository;

import jakarta.persistence.NoResultException;

public class PgProductRepository extends PgRepository
    implements ICreateProductRepository, IFindProductByNameRepository {
  public UUID create(Product product) {
    PgProduct pgProduct = new PgProduct(product.getName(), product.getPrice());
    this.session.persist(pgProduct);

    return pgProduct.getID();
  }

  public Product findByName(String name) {
    try {
      PgProduct pgProduct = this.session.createQuery("FROM product p WHERE p.name = :name", PgProduct.class)
          .setParameter("name", name)
          .getSingleResult();

      return new Product(pgProduct.getID(), pgProduct.getName(), pgProduct.getPrice());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }
}
