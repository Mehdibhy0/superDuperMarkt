package org.example.repository;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {return products;};

    public void addProduct(Product product) {
        products.add(product);
    }
}
