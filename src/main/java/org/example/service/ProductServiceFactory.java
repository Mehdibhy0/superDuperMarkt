package org.example.service;

import org.example.model.Cheese;
import org.example.model.Joghurt;
import org.example.model.Wine;
import org.example.repository.InMemoryProductRepository;
import org.example.sqlmodule.SqlProductRepository;
import org.example.utils.CsvProductLoader;

import java.time.LocalDate;

public class ProductServiceFactory {
    public static ProductService createSampleService() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        repository.addProduct(new Cheese("Gouda", 50, 10.0, LocalDate.now().plusDays(60)));
        repository.addProduct(new Cheese("Brie", 40, 12.0, LocalDate.now().plusDays(50)));
        repository.addProduct(new Cheese("Ricotta", 40, 15.0, LocalDate.now().plusDays(5)));
        //Not comsumable when quality is 0, or one Day after Expiry / price is cut in half on 5th day
        repository.addProduct(new Joghurt("Joghurt", 7, 15.0, LocalDate.now().plusDays(10)));

        // Non-Perishable Products: Wine
        repository.addProduct(new Wine("Merlot", 20, 30.0, LocalDate.now().minusDays(5)));
        repository.addProduct(new Wine("Chardonnay", 10, 25.0, LocalDate.now().minusDays(10)));
        repository.addProduct(new Wine("Cabernet Sauvignon", 47, 40.0, LocalDate.now().minusDays(15)));
        return new ProductService(repository);
    }

    public static ProductService createCsvService(String pathToCsv) {
        InMemoryProductRepository productRepository = new InMemoryProductRepository();

        CsvProductLoader loader = new CsvProductLoader();
        loader.load(pathToCsv, productRepository);

        return new ProductService(productRepository);
    }

    public static ProductService createSqlService(String url, String name, String password) {
        SqlProductRepository repository = new SqlProductRepository(url, name, password);
        return new ProductService(repository);
    }
}
