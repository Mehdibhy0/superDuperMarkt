package org.example.service;

import org.example.repository.InMemoryProductRepository;
import org.example.sqlmodule.SqlProductRepository;
import org.example.utils.CsvProductLoader;
import org.example.utils.SampleDataLoader;

import java.time.LocalDate;

public class ProductServiceFactory {
    //Create service for hardcoded data
    public static ProductService createSampleService() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        SampleDataLoader loader = new SampleDataLoader(LocalDate.now());
        loader.load(repository);
        return new ProductService(repository);
    }
    //Create service for csv data
    public static ProductService createCsvService(String pathToCsv) {
        InMemoryProductRepository productRepository = new InMemoryProductRepository();

        CsvProductLoader loader = new CsvProductLoader();
        loader.load(pathToCsv, productRepository);

        return new ProductService(productRepository);
    }

    //Create service for SQL data
    public static ProductService createSqlService(String url, String name, String password) {
        SqlProductRepository repository = new SqlProductRepository(url, name, password);
        return new ProductService(repository);
    }
}
