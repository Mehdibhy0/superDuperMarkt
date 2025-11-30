package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//Products that Expire
public abstract class PerishableProduct extends Product{
    protected final LocalDate expiryDate;

    public PerishableProduct(String name, int quality, double basePrice,  LocalDate expiryDate) {
        super(name, quality, basePrice, false);
        this.expiryDate = expiryDate;
    }
}

