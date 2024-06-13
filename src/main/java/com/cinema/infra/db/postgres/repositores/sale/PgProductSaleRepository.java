package com.cinema.infra.db.postgres.repositores.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICreateProductSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IFindProductsSaleByProductID;
import com.cinema.domain.entities.sale.ProductSale;
import com.cinema.infra.db.postgres.entities.sale.PgProductSale;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgProductSaleRepository extends PgRepository
    implements IFindProductsSaleByProductID, ICreateProductSaleRepository {

  @Override
  public List<ProductSale> findProductsSaleByProductID(UUID productID) {
    List<PgProductSale> pgProducts = this.session
        .createQuery("from product_sale where product.id = :productID",
            PgProductSale.class)
        .setParameter("productID", productID)
        .setCacheable(false)
        .getResultList();

    return pgProducts.stream().map(pgProduct -> {
      return ConvertEntities.convertProductSale(pgProduct);
    }).toList();
  }

  @Override
  public UUID create(ProductSale productSale) {
    PgProductSale pgProductSale = ConvertEntities.pgConvertProductSale(productSale);

    System.out.println(pgProductSale.getSale().getID());

    this.session.persist(pgProductSale);

    return pgProductSale.getID();
  }
}
