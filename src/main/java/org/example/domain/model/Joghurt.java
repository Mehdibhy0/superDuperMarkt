package org.example.domain.model;

import java.time.LocalDate;

public class Joghurt extends PerishableProduct{
    public Joghurt(String name, int quality, double basePrice, LocalDate expiryDate) {
        super(name, quality, basePrice, expiryDate);
    }
}
