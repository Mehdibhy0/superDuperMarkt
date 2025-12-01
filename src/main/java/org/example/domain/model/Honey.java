package org.example.domain.model;

public class Honey extends NonPerishableProduct{
    public Honey(String name, int quality, double basePrice) {
        super(name, quality, basePrice, false);
    }
}
