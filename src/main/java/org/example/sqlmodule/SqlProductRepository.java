package org.example.sqlmodule;

import org.example.domain.model.Cheese;
import org.example.domain.model.Honey;
import org.example.domain.model.Joghurt;
import org.example.domain.model.Product;
import org.example.domain.model.Wine;
import org.example.repository.ProductRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SqlProductRepository implements ProductRepository {
    private final String url;
    private final String username;
    private final String password;

    private List<Product> cache;

    public SqlProductRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public List<Product> getProducts() {
        if (cache == null) {
            cache = loadFromDatabase();
        }
        return cache;
    }

    public List<Product> loadFromDatabase() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select * from products;")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                int quality = rs.getInt("quality");
                double basePrice = rs.getDouble("base_price");
                Date expiry = rs.getDate("expiry_date");
                Date stock = rs.getDate("stock_date");

                LocalDate expiryDate = expiry != null ? expiry.toLocalDate() : null;
                LocalDate stockDate = stock != null ? stock.toLocalDate() : null;

                Product p;
                switch (type) {
                    case "Cheese" -> p = new Cheese(name, quality, basePrice, expiryDate);
                    case "Joghurt" -> p = new Joghurt(name, quality, basePrice, expiryDate);
                    case "Wine" -> p = new Wine(name, quality, basePrice, stockDate);
                    case "Honey" -> p = new Honey(name, quality, basePrice);
                    default -> throw new IllegalArgumentException("Invalid type");
                }

                p.setId(id);
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void addProduct(Product product) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO products (type, name, quality, base_price, expiry_date, stock_date) VALUES (?,?,?,?,?,?)")) {
            ps.setString(1, product.getClass().getSimpleName());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getQuality());
            ps.setDouble(4, product.getBasePrice());

            switch (product) {
                case Cheese c -> {
                    ps.setDate(5, Date.valueOf(c.getExpiryDate()));
                    ps.setNull(6, Types.DATE);
                }
                case Joghurt j -> {
                    ps.setDate(5, Date.valueOf(j.getExpiryDate()));
                    ps.setNull(6, Types.DATE);
                }
                case Wine w -> {
                    ps.setNull(5, Types.DATE);
                    ps.setDate(6, Date.valueOf(w.getStockDate()));
                }
                default -> {
                    ps.setNull(5, Types.DATE);
                    ps.setNull(6, Types.DATE);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
