package org.example;

import org.example.model.Product;
import org.example.service.ProductService;

import java.time.LocalDate;

public class Simulator {

    private final ProductService productService;

    public Simulator(ProductService productService) {
        this.productService = productService;
    }

    public void run(LocalDate startDate, int days) {
        for (int day = 0; day <= days; day++) {
            LocalDate currentDate = startDate.plusDays(day);
            System.out.println("Day: " + (day + 1) + " Current date: " + currentDate);
            productService.updateAllProducts(currentDate);

            for (Product p : productService.getProducts()) {
                System.out.printf("%s | Quality: %d | Base Price: %.2f | CurrentPrice: %.2f | Remove: %s%n", p.getName(), p.getQuality(), p.getBasePrice(), p.getCurrentPrice(), p.isMustBeRemoved() ? "Yes" : "No");
            }

            System.out.println("____________________________________");
            System.out.println("");
        }
    }
}
