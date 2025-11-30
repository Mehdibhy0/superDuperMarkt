package org.example.utils;

import org.example.model.Cheese;
import org.example.model.Joghurt;
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

    //Joghurt has quality of 7 , expires after 7 days, and becomes half price after 5th day
    public void updateJoghurt(Joghurt joghurt, LocalDate currentDate) {
        boolean isExpired = !joghurt.getExpiryDate().isAfter(currentDate);
        if (joghurt.getQuality() > 0) {
            joghurt.setQuality(joghurt.getQuality() - 1);
        }
        if (isExpired) {joghurt.setMustBeRemoved(true);}
    }
}
