package org.example.model;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Wine extends NonPerishableProduct {
    private final LocalDate stockDate;
    private final int initialQuantity;

    public Wine(String name, int quality, Double basePrice , LocalDate stockDate) {
        super(name, quality, basePrice, false);
        this.stockDate = stockDate;
        this.initialQuantity = quality;
    }

}
