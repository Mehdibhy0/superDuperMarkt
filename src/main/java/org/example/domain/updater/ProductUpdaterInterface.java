package org.example.domain.updater;

import org.example.domain.model.Product;

import java.time.LocalDate;

public interface ProductUpdaterInterface {
    void update(Product product, LocalDate currentDate);

    double calculatePrice(Product product);
}