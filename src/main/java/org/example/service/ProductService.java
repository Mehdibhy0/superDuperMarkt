package org.example.service;

import org.example.model.Cheese;
import org.example.model.Joghurt;
import org.example.model.NonPerishableProduct;
import org.example.model.PerishableProduct;
import org.example.model.Product;
import org.example.model.Wine;
import org.example.repository.ProductRepository;
import org.example.utils.ProductUpdaterRegistry;
import org.example.utils.productupdater.CheeseUpdater;
import org.example.utils.productupdater.DefaultUpdater;
import org.example.utils.productupdater.GenericNonPerishableProductUpdater;
import org.example.utils.productupdater.GenericPerishableProductUpdater;
import org.example.utils.productupdater.JoghurtUpdater;
import org.example.utils.productupdater.WineUpdater;

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
