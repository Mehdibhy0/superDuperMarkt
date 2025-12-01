package org.example.domain.updater;

import org.example.domain.model.Joghurt;
import org.example.domain.model.Product;

import java.time.LocalDate;

public class JoghurtUpdater implements ProductUpdaterInterface {
    @Override
    public void update(Product product, LocalDate currentDate) {
        Joghurt joghurt = (Joghurt) product;
        boolean isExpired = !joghurt.getExpiryDate().isAfter(currentDate);

        if (joghurt.getQuality() > 0) {
            joghurt.setQuality(joghurt.getQuality() - 1);
        }
        joghurt.setMustBeRemoved(isExpired || joghurt.getQuality() == 0);
    }

    @Override
    public double calculatePrice(Product product) {
        Joghurt joghurt = (Joghurt) product;
        if (joghurt.getQuality() <= 2) {
            return (joghurt.getBasePrice() / 2);
        }
        return joghurt.getBasePrice() + joghurt.getQuality() * 0.10;
    }
}
