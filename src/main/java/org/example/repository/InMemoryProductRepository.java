package org.example.repository;

import org.example.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository{
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {return products;};

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }
}
