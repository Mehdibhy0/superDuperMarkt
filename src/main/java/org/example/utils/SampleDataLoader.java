package org.example.utils;

import org.example.domain.model.Cheese;
import org.example.domain.model.Joghurt;
import org.example.domain.model.Wine;
import org.example.repository.ProductRepository;

import java.time.LocalDate;

public class SampleDataLoader {
    private final LocalDate today;

    public SampleDataLoader(LocalDate today) {
        this.today = today;
    }

    public void load(ProductRepository repository) {
        repository.addProduct(new Cheese("Gouda", 50, 10.0, today.plusDays(60)));
        repository.addProduct(new Cheese("Brie", 40, 12.0, today.plusDays(50)));
        repository.addProduct(new Cheese("Ricotta", 40, 15.0, today.plusDays(5)));
        //Not comsumable when quality is 0, or one Day after Expiry / price is cut in half on 5th day
        repository.addProduct(new Joghurt("Joghurt", 7, 15.0, today.plusDays(10)));

        // Non-Perishable Products: Wine
        repository.addProduct(new Wine("Merlot", 20, 30.0, today.minusDays(5)));
        repository.addProduct(new Wine("Chardonnay", 10, 25.0, today.minusDays(10)));
        repository.addProduct(new Wine("Cabernet Sauvignon", 47, 40.0, today.minusDays(15)));
    }
}
