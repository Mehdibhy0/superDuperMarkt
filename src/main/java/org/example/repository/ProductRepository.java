package org.example.repository;

import org.example.domain.model.Product;

import java.util.List;

public interface ProductRepository
{
	List<Product> getProducts();
    void addProduct(Product product);
}
