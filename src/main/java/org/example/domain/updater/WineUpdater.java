package org.example.domain.updater;

import org.example.domain.model.Product;
import org.example.domain.model.Wine;

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
        int initialQuality = wine.getInitialQuality();
        int newQuality = Math.min(50, initialQuality + increments);

        wine.setQuality(newQuality);
        wine.setMustBeRemoved(false);
    }

    @Override
    public double calculatePrice(Product product) {
        if (!(product instanceof Wine)) {
            throw new IllegalArgumentException("Expected Wine Product, got: " + product.getClass());
        }
        return product.getBasePrice();
    }
}
