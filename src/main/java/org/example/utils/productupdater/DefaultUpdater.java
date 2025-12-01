package org.example.utils.productupdater;

import org.example.model.Product;

import java.time.LocalDate;

public class DefaultUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {

    }

    @Override
    public double calculatePrice(Product product) {
        return product.getBasePrice() + product.getQuality() * 0.1;
    }
}
