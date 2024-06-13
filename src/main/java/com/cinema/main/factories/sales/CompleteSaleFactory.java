package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.CompleteSaleController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.CompleteSaleDTO;
import com.cinema.domain.usecases.sale.CompleteSaleUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgCartRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgProductCartRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgProductSaleRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgSaleRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgTicketCartRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgTicketSaleRepository;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;

public class CompleteSaleFactory {
  public static Controller<CompleteSaleDTO> make() {
    PgTicketCartRepository ticketCartRepository = new PgTicketCartRepository();
    PgProductCartRepository productCartRepository = new PgProductCartRepository();
    PgPersonRepository personRepository = new PgPersonRepository();
    PgSaleRepository saleRepository = new PgSaleRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();
    PgTicketSaleRepository ticketSaleRepository = new PgTicketSaleRepository();
    PgProductSaleRepository productSaleRepository = new PgProductSaleRepository();
    PgCartRepository cartRepository = new PgCartRepository();

    CompleteSaleUseCase useCase = new CompleteSaleUseCase(
        ticketCartRepository, productCartRepository, personRepository, saleRepository,
        saleRepository, inventoryRepository, ticketSaleRepository, productSaleRepository, inventoryRepository,
        ticketSaleRepository, productSaleRepository, cartRepository);

    CompleteSaleController controller = new CompleteSaleController(useCase);

    PgConnection connection = PgConnection.getInstance();

    return new DbTransactionController<>(controller, connection);
  }
}
