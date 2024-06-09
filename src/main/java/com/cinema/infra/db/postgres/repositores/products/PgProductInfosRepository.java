package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateProductInfosRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteProductInfosRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductInfosByIdRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductInfosByNameRepository;
import com.cinema.domain.contracts.repositories.products.IListProductsInfosRepository;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.infra.db.postgres.entities.products.PgProductInfos;
import com.cinema.infra.db.postgres.repositores.PgRepository;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PgProductInfosRepository extends PgRepository
    implements ICreateProductInfosRepository, IFindProductInfosByNameRepository, IDeleteProductInfosRepository,
    IFindProductInfosByIdRepository, IListProductsInfosRepository {
  public UUID create(ProductInfos product) {
    PgProductInfos pgProduct = new PgProductInfos(product.getName(), product.getPrice());
    this.session.persist(pgProduct);

    return pgProduct.getID();
  }

  public ProductInfos findByName(String name) {
    try {
      PgProductInfos pgProduct = this.session
          .createQuery("FROM product_infos p WHERE p.name = :name", PgProductInfos.class)
          .setParameter("name", name)
          .getSingleResult();

      return new ProductInfos(pgProduct.getID(), pgProduct.getName(), pgProduct.getPrice());
    } catch (NoResultException e) {
      return null;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<ProductInfos> listProductsInfos() {
    return this.session.createQuery("FROM product_infos p", PgProductInfos.class)
        .getResultList()
        .stream()
        .map(
            pgProduct -> new ProductInfos(
                pgProduct.getID(),
                pgProduct.getName(),
                pgProduct.getPrice()))
        .collect(Collectors.toList());
  }

  public void deleteProduct(UUID id) {
    PgProductInfos pgProduct = this.session.find(PgProductInfos.class, id);
    this.session.remove(pgProduct);
  }

  public ProductInfos findById(UUID id) {
    PgProductInfos pgProduct = this.session.find(PgProductInfos.class, id);
    return new ProductInfos(
        pgProduct.getID(),
        pgProduct.getName(),
        pgProduct.getPrice());
  }
}
