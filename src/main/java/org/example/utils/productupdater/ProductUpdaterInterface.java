package org.example.utils.productupdater;

import org.example.model.Product;

import java.time.LocalDate;

public interface ProductUpdaterInterface {
    void update(Product product, LocalDate currentDate);

    double calculatePrice(Product product);
}