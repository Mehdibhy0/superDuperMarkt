package org.example.utils;

import org.example.model.Product;
import org.example.utils.productupdater.GenericNonPerishableProductUpdater;
import org.example.utils.productupdater.ProductUpdaterInterface;

import java.util.HashMap;
import java.util.Map;

public class ProductUpdaterRegistry {
    private final Map<Class<? extends Product>, ProductUpdaterInterface> registry = new HashMap<>();
    private final ProductUpdaterInterface productUpdater;

    public ProductUpdaterRegistry(ProductUpdaterInterface productUpdater1) {
        this.productUpdater = productUpdater1;
    }

    public void register(Class<? extends Product> type, ProductUpdaterInterface productUpdater) {
        registry.put(type, productUpdater);
    }

    public ProductUpdaterInterface getUpdater(Product product) {
        Class<?> cls = product.getClass();

        //check exact match
        if (registry.containsKey(cls)) {
            return registry.get(cls);
        }
        //try parent classes Perishable and NonPerishable
        for (Class<?> key : registry.keySet()) {
            if (key.isAssignableFrom(cls)) {
                return registry.get(key);
            }
        }

        return new GenericNonPerishableProductUpdater();
    }

}
