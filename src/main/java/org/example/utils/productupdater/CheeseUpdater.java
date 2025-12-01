package org.example.utils.productupdater;

import org.example.model.Cheese;
import org.example.model.Product;
import org.example.model.Wine;

import java.time.LocalDate;

public class CheeseUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {
        if (!(product instanceof Cheese cheese)) {
            throw new IllegalArgumentException("Expected Wine Product, got: " + product.getClass());
        }
        cheese.setQuality(cheese.getQuality()-1);
        boolean isLowQuality = cheese.getQuality() < 30;
        boolean isExpired = !cheese.getExpiryDate().isAfter(currentDate);
        cheese.setMustBeRemoved(isLowQuality || isExpired);
    }

    @Override
    public double calculatePrice(Product product) {
        if (!(product instanceof Cheese cheese)) {
            throw new IllegalArgumentException("Expected Wine Product, got: " + product.getClass());
        }
        return cheese.getBasePrice() + cheese.getQuality() * 0.1;
    }
}
