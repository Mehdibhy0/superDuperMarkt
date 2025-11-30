package org.example.service;

import org.example.model.Cheese;
import org.example.model.Joghurt;
import org.example.model.NonPerishableProduct;
import org.example.model.PerishableProduct;
import org.example.model.Product;
import org.example.model.Wine;
import org.example.repository.ProductRepository;
import org.example.utils.ProductUpdateHelper;

import java.time.LocalDate;
import java.util.List;

public class ProductService {

    private final ProductRepository repository;
    private final ProductUpdateHelper helper = new ProductUpdateHelper();

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

    public void updateAllProducts(LocalDate currentDate) {
        for (Product product : repository.getProducts()) {
            if (product instanceof PerishableProduct perishableProduct) {
                updatePerishableProduct(perishableProduct, currentDate);
            } else if (product instanceof NonPerishableProduct nonPerishableProduct) {
                updateNonPerishableProduct(nonPerishableProduct, currentDate);
            }

            product.setCurrentPrice(calculateCurrentPrice(product));
        }
    }

    public double calculateCurrentPrice(Product product) {
        //Wine does not change price
        if (product instanceof Wine) {
            return product.getBasePrice();
        } else if (product instanceof Joghurt joghurt) {
            if (joghurt.getQuality() <= 2) {
                return (joghurt.getBasePrice() / 2);
            }
        }
        return product.getBasePrice() + (product.getQuality() * 0.10);
    }

    // Update Products that Do Expire
    public void updatePerishableProduct(PerishableProduct product, LocalDate currentDate) {
        if (product instanceof Cheese c) {
            helper.updateCheese(c, currentDate);
        } else if (product instanceof Joghurt j) {
            helper.updateJoghurt(j, currentDate);
        } else {
            updateGenericPerishableProduct(product, currentDate);
        }
    }

    public void updateGenericPerishableProduct(PerishableProduct product, LocalDate currentDate) {
        //mustBeRemoved depends on Expiration date
        product.setMustBeRemoved(!product.getExpiryDate().isAfter(currentDate));
    }

    public void updateNonPerishableProduct(NonPerishableProduct product, LocalDate currentDate) {
        if (product instanceof Wine wine) {
            helper.updateWine(wine, currentDate);
        }
        updateGenericNonPerishableProduct(product);
    }

    public void updateGenericNonPerishableProduct(Product product) {
        product.setMustBeRemoved(false);
    }
}
