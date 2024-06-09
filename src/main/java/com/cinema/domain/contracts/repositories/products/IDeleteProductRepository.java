package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

public interface IDeleteProductRepository {
    public void deleteProduct(UUID id);
}
