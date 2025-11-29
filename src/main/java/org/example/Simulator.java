package org.example;

import org.example.model.Cheese;
import org.example.model.Product;
import org.example.model.Wine;
import org.example.repository.InMemoryProductRepository;
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
            System.out.println("____________________________________");

            productService.updateAllProducts(currentDate);

            for (Product p : productService.getProducts()) {
                System.out.printf("%s | Quality: %d | Base Price: %.2f | CurrentPrice: %.2f | Remove: %b%n",
                        p.getName(),
                        p.getQuality(),
                        p.getBasePrice(),
                        p.getCurrentPrice(),
                        p.isMustBeRemoved());
            }
            System.out.println("");

        }
    }

    public static ProductService createSampleService() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        repository.addProduct(new Cheese("Gouda", 50, 10.0, LocalDate.now().plusDays(60)));
        repository.addProduct(new Cheese("Brie", 40, 12.0, LocalDate.now().plusDays(50)));
        repository.addProduct(new Cheese("Ricotta", 40, 15.0, LocalDate.now().plusDays(5)));

        // Non-Perishable Products: Wine
        repository.addProduct(new Wine("Merlot", 20, 30.0, LocalDate.now().minusDays(5)));
        repository.addProduct(new Wine("Chardonnay", 10, 25.0, LocalDate.now().minusDays(10)));
        repository.addProduct(new Wine("Cabernet Sauvignon", 47, 40.0, LocalDate.now().minusDays(15)));
        return new ProductService(repository);
    }
}
