package org.example.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Product {
    protected int id;
    protected final String name;
    protected int quality;
    protected final double basePrice;
    protected double currentPrice;
    protected boolean mustBeRemoved;

    protected Product(String name, int quality, double basePrice, boolean mustBeRemoved) {
        this.name = name;
        this.quality = quality;
        this.basePrice = basePrice;
        this.mustBeRemoved = false;
    }
}
