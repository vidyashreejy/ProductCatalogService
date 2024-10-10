package org.example.services;

import org.example.models.Product;

import java.util.List;

public interface IFakeStoreProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);
}
