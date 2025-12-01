package org.example.service;

import org.example.domain.model.Cheese;
import org.example.domain.model.Joghurt;
import org.example.domain.model.NonPerishableProduct;
import org.example.domain.model.PerishableProduct;
import org.example.domain.model.Product;
import org.example.domain.model.Wine;
import org.example.repository.ProductRepository;
import org.example.utils.ProductUpdaterRegistry;
import org.example.domain.updater.CheeseUpdater;
import org.example.domain.updater.DefaultUpdater;
import org.example.domain.updater.GenericNonPerishableProductUpdater;
import org.example.domain.updater.GenericPerishableProductUpdater;
import org.example.domain.updater.JoghurtUpdater;
import org.example.domain.updater.WineUpdater;

import java.time.LocalDate;
import java.util.List;

public class ProductService {
    private final ProductRepository repository;
    private final ProductUpdaterRegistry registry;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.registry = new ProductUpdaterRegistry(new DefaultUpdater());
        registry.register(PerishableProduct.class, new GenericPerishableProductUpdater());
        registry.register(NonPerishableProduct.class, new GenericNonPerishableProductUpdater());
        registry.register(Wine.class, new WineUpdater());
        registry.register(Cheese.class, new CheeseUpdater());
        registry.register(Joghurt.class, new JoghurtUpdater());
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

    public void updateAllProducts(LocalDate currentDate) {
        for (Product product : repository.getProducts()) {
            var updater = registry.getUpdater(product);
            updater.update(product, currentDate);

            var currentPrice = updater.calculatePrice(product);
            product.setCurrentPrice(currentPrice);
        }
    }
}
