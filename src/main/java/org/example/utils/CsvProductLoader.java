package org.example.utils;

import org.example.domain.model.Cheese;
import org.example.domain.model.Honey;
import org.example.domain.model.Joghurt;
import org.example.domain.model.Product;
import org.example.domain.model.Wine;
import org.example.repository.InMemoryProductRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class CsvProductLoader {
    public void load(String fileName, InMemoryProductRepository productRepository) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line = br.readLine(); // to skip header
                while ((line = br.readLine()) != null) {
                    if (line.isBlank()) {
                        continue;
                    }

                    Product p = parseLine(line);
                    productRepository.addProduct(p);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load CSV: " + fileName, e);
        }
    }

    private Product parseLine(String line) {
        String[] parts = line.split(",");

        String type = parts[0].trim().toLowerCase();
        String name = parts[1].trim();
        int quality = Integer.parseInt(parts[2].trim());
        double price = Double.parseDouble(parts[3].trim());
        LocalDate date = LocalDate.parse(parts[4].trim());

        return switch (type) {
            case "cheese" -> new Cheese(name, quality, price, date);
            case "wine" -> new Wine(name, quality, price, date);
            case "joghurt" -> new Joghurt(name, quality, price, date);
            case "honey" -> new Honey(name, quality, price);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}
