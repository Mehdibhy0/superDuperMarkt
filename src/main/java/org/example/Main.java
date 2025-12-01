package org.example;

import org.example.service.ProductServiceFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Uncomment Next Line to Run  with hardcoded data
        //___________________________________________________
        var service = ProductServiceFactory.createSampleService();
        //___________________________________________________


        //Uncomment Next Line to Run with data received from CSV file ''products.csv''
        //___________________________________________________
        // var service = ProductServiceFactory.createCsvService("products.csv");
        //___________________________________________________


        // Uncomment Next Section to Run with data received from SQL database
        //___________________________________________________
//        String url = "jdbc:mysql://localhost:3306/superduper?serverTimezone=UTC";
//        String username = "superuser";
//        String password = "SuperDuper123!";
//        var service = ProductServiceFactory.createSqlService(url, username, password);
        //___________________________________________________

        Simulator simulator = new Simulator(service);
        simulator.run(LocalDate.now(), 20);
    }
}