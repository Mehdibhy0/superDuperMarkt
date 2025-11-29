package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Product {
    protected final String name;
    protected int quality;
    protected final double basePrice;
    protected  double currentPrice;
    protected boolean mustBeRemoved;
}
