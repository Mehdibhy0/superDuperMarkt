package org.example.model;

import java.time.LocalDate;

//Products that do not Expire
public abstract class NonPerishableProduct extends Product {
    public NonPerishableProduct(String name, int quality, double basePrice, boolean mustBeRemoved) {
        super(name, quality, basePrice, 0.0,  mustBeRemoved);
    }
}
