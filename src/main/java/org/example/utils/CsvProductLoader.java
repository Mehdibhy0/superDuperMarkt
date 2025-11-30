package org.example.utils;

import org.example.model.Cheese;
import org.example.model.Honey;
import org.example.model.Joghurt;
import org.example.model.Product;
import org.example.model.Wine;
import org.example.repository.InMemoryProductRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class CsvProductLoader {
    public void load(String filePath, InMemoryProductRepository productRepository) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line = br.readLine(); // to skip header
            while((line = br.readLine()) != null){
                if (line.isBlank()) continue;

                Product p = parseLine(line);
                productRepository.addProduct(p);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load CSV: " + filePath, e);
        }
    }

    private Product parseLine(String line){
        String[] parts = line.split(",");

        String type = parts[0].trim().toLowerCase();
        String name = parts[1].trim();
        int quality = Integer.parseInt(parts[2].trim());
        double price = Double.parseDouble(parts[3].trim());
        LocalDate date = LocalDate.parse(parts[4].trim());

        return switch (type){
            case "cheese" -> new Cheese(name, quality, price, date);
            case "wine" -> new Wine(name, quality, price, date);
            case "joghurt" -> new Joghurt(name, quality, price, date);
            case "honey" -> new Honey(name, quality, price);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}
