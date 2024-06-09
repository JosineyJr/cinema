package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

public interface IDeleteProductInfosRepository {
    public void deleteProduct(UUID id);
}
