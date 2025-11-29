package org.example.model;


import java.time.LocalDate;

public class Cheese extends PerishableProduct {
    public Cheese(String name, int quality, double basePrice , LocalDate expiryDate) {
        super(name, quality, basePrice, expiryDate);
    }
}
