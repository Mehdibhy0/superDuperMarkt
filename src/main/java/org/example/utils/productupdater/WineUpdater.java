package org.example.utils.productupdater;

import org.example.model.Product;
import org.example.model.Wine;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WineUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {
        if (!(product instanceof Wine wine)) {
            throw new IllegalArgumentException("Expected Wine Product, got: " + product.getClass());
        }

        long daySinceStock = ChronoUnit.DAYS.between(wine.getStockDate(), currentDate);
        if (daySinceStock <= 0) {
            return;
        }

        int increments = (int) daySinceStock / 10;
        int initialQuality = wine.getInitialQuantity();
        int newQuality = Math.min(50, initialQuality + increments);

        wine.setQuality(newQuality);
        wine.setMustBeRemoved(false);
    }

    @Override
    public double calculatePrice(Product product) {
        if (!(product instanceof Wine wine)) {
            throw new IllegalArgumentException("Expected Wine Product, got: " + product.getClass());
        }
        return product.getBasePrice();
    }
}
