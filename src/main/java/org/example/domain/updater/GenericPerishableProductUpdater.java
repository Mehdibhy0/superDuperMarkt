package org.example.domain.updater;

import org.example.domain.model.PerishableProduct;
import org.example.domain.model.Product;

import java.time.LocalDate;

public class GenericPerishableProductUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {
        if (!(product instanceof PerishableProduct perishableProduct)) {
            throw new IllegalArgumentException("The product is not a PerishableProduct");
        }
        product.setMustBeRemoved(!perishableProduct.getExpiryDate().isAfter(currentDate));
    }

    @Override
    public double calculatePrice(Product product) {
        return product.getBasePrice() + product.getQuality() * 0.1;
    }
}
