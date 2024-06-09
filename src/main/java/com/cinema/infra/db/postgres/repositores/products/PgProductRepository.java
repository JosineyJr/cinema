package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIdRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.entities.products.ProductInfo;
import com.cinema.infra.db.postgres.entities.products.PgProductInfos;
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

public class PgProductRepository extends PgRepository
    implements ICreateProductRepository, IFindProductByNameRepository {
  public UUID create(ProductInfo product) {
    PgProductInfos pgProduct = new PgProductInfos(product.getName(), product.getPrice());
    this.session.persist(pgProduct);

    return pgProduct.getID();
  }

  public ProductInfo findByName(String name) {
    try {
      PgProductInfos pgProduct = this.session.createQuery("FROM product p WHERE p.name = :name", PgProductInfos.class)
          .setParameter("name", name)
          .getSingleResult();

      return new ProductInfo(pgProduct.getID(), pgProduct.getName(), pgProduct.getPrice());
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
