package org.example.utils;

import org.example.model.Cheese;
import org.example.model.Wine;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductUpdateHelper {
    public void updateCheese(Cheese cheese, LocalDate currentDate) {
        cheese.setQuality(cheese.getQuality() - 1);

        boolean isLowQuality = cheese.getQuality() < 30;
        boolean isExpired = !cheese.getExpiryDate().isAfter(currentDate);
        cheese.setMustBeRemoved(isLowQuality || isExpired);
    }

    public void updateWine(Wine wine, LocalDate currentDate) {
        long days = ChronoUnit.DAYS.between(wine.getStockDate(), currentDate);
        if (days > 0 && days % 10 == 0 && wine.getQuality() < 50) {
            wine.setQuality(wine.getQuality() + 1);
        }
    }
}
