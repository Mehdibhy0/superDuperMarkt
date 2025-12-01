package org.example.domain.updater;

import org.example.domain.model.Product;

import java.time.LocalDate;

public class GenericNonPerishableProductUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {
        product.setMustBeRemoved(false);
    }

    @Override
    public double calculatePrice(Product product) {
        return product.getBasePrice() + product.getQuality() * 0.1;
    }
}
