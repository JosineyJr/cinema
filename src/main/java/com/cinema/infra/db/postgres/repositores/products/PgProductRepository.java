package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIDRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.contracts.repositories.products.IListProductsRepository;
import com.cinema.domain.contracts.repositories.products.IUpdateProductRepository;
import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PgProductRepository
    extends PgRepository
    implements ICreateProductRepository,
    IFindProductByNameRepository,
    IDeleteProductRepository,
    IFindProductByIDRepository,
    IListProductsRepository,
    IUpdateProductRepository {

  public UUID create(Product product) {
    PgProduct pgProduct = ConvertEntities.pgConvertProduct(product);
    this.session.persist(pgProduct);

    return pgProduct.getID();
  }

  public Product findByName(String name) {
    try {
      PgProduct pgProduct = this.session
          .createQuery("FROM product p WHERE p.name = :name", PgProduct.class)
          .setParameter("name", name)
          .getSingleResult();

      return ConvertEntities.convertProduct(pgProduct);
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
            pgProduct -> ConvertEntities.convertProduct(pgProduct))
        .collect(Collectors.toList());
  }

  public void deleteProduct(UUID id) {
    PgProduct pgProduct = this.session.find(PgProduct.class, id);
    this.session.remove(pgProduct);
  }

  public Product findById(UUID id) {
    PgProduct pgProduct = this.session.find(PgProduct.class, id);
    return ConvertEntities.convertProduct(pgProduct);
  }

  public Product updateProduct(Product product) {
    PgProduct pgProduct = this.session.get(PgProduct.class, product.getID());
    pgProduct.setName(product.getName());
    pgProduct.setPrice(product.getPrice());

    this.session.persist(pgProduct);

    return product;
  }
}
