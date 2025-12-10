package org.example.domain.model;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Wine extends NonPerishableProduct {
    private final LocalDate stockDate;
    private final int initialQuality;

    public Wine(String name, int quality, Double basePrice, LocalDate stockDate) {
        super(name, quality, basePrice, false);
        this.stockDate = stockDate;
        this.initialQuality = quality;
    }

}
