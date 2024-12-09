package com.cinema.main.routes.products;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.products.CreateProductDTO;
import com.cinema.application.dtos.products.DeleteProductDTO;
import com.cinema.application.dtos.products.EditProductDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.products.CreateProductFactory;
import com.cinema.main.factories.products.DeleteProductFactory;
import com.cinema.main.factories.products.ListProductsFactory;
import com.cinema.main.factories.products.UpdateProductFactory;

@RestController
public class ProductRoutes {
  private final SpringAdapter<Object> listProductsAdapter;
  private final SpringAdapter<CreateProductDTO> createProductAdapter;
  private final SpringAdapter<DeleteProductDTO> deleteProductAdapter;
  private final SpringAdapter<EditProductDTO> updateProductAdapter;

  public ProductRoutes() {
    this.listProductsAdapter = new SpringAdapter<>(ListProductsFactory.make());
    this.createProductAdapter = new SpringAdapter<>(CreateProductFactory.make());
    this.deleteProductAdapter = new SpringAdapter<>(DeleteProductFactory.make());
    this.updateProductAdapter = new SpringAdapter<>(UpdateProductFactory.make());
  }

  @GetMapping("/products")
  public ResponseEntity<?> getProducts() {
    return this.listProductsAdapter.adapt(null);
  }

  @PostMapping("/product")
  public ResponseEntity<?> postProduct(@RequestBody CreateProductDTO product) {
    return createProductAdapter.adapt(product);
  }

  @DeleteMapping("/product/{productId}/{inventoryId}")
  public ResponseEntity<?> deleteProduct(@PathVariable UUID productId, @PathVariable UUID inventoryId) {
    DeleteProductDTO product = new DeleteProductDTO(productId, inventoryId);
    return deleteProductAdapter.adapt(product);
  }

  @PutMapping("/product")
  public ResponseEntity<?> updateProduct(@RequestBody EditProductDTO product) {
    return updateProductAdapter.adapt(product);
  }
}
