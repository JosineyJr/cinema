package com.cinema.infra.db.postgres.helpers.entities.sales;

import java.util.List;
import java.util.ArrayList;

import com.cinema.domain.entities.sale.ProductSale;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.TicketSale;
import com.cinema.infra.db.postgres.entities.sale.PgProductSale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgTicketSale;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.users.PersonConverter;

public class SaleConverter implements IEntityConverter<PgSale, Sale> {
    private TicketSaleConverter ticketSaleConverter = new TicketSaleConverter();
    private ProductSaleConverter productSaleConverter = new ProductSaleConverter();
    private PersonConverter personConverter = new PersonConverter();

    @Override
    public Sale convert(PgSale source) {
        List<TicketSale> ticketsSale = new ArrayList<>();

        if (source.getTickets() == null) {
            source.setTickets(new ArrayList<>());
        }

        source.getTickets().stream().forEach(pgTicketSale -> {
            TicketSale ticketSale = ticketSaleConverter.convert(pgTicketSale);

            ticketsSale.add(ticketSale);
        });

        List<ProductSale> productsSale = new ArrayList<>();

        if (source.getProducts() == null) {
            source.setProducts(new ArrayList<>());
        }

        source.getProducts().stream().forEach(pgProductSale -> {
            ProductSale productSale = productSaleConverter.convert(pgProductSale);

            productsSale.add(productSale);
        });

        return new Sale(source.getID(), ticketsSale, productsSale, source.getTotalPrice(), source.getSaleDate(),
                personConverter.convert(source.getPerson()), null);
    }

    @Override
    public PgSale pgConverter(Sale target) {
        List<PgTicketSale> pgTicketsSale = new ArrayList<>();

        if (target.getTicketSales() == null) {
            target.setTicketSales(new ArrayList<>());
        }

        target.getTicketSales().stream().forEach(ticketSale -> {
            pgTicketsSale.add(ticketSaleConverter.pgConverter(ticketSale));
        });

        List<PgProductSale> pgProductsSale = new ArrayList<>();

        if (target.getProductSales() == null) {
            target.setProductSales(new ArrayList<>());
        }

        target.getProductSales().stream().forEach(productSale -> {
            pgProductsSale.add(productSaleConverter.pgConverter(productSale));
        });

        return new PgSale(target.getID(), pgProductsSale, pgTicketsSale, target.getTotalPrice(), target.getSaleDate(),
                null, personConverter.pgConverter(target.getPerson()));
    }

}
