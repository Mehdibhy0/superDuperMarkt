package org.example;

import org.example.service.ProductServiceFactory;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Uncomment Next Line to Run  with hardcoded data
//        var service = ProductServiceFactory.createSampleService();
        //___________________________________________________


        //Uncomment Next Line to Run with data received from CSV file ''products.csv''
        var service = ProductServiceFactory.createCsvService("products.csv");
        //___________________________________________________


//         Uncomment Next Line to Run with data received from Sql database
//        String url = "jdbc:mysql://localhost:3306/superduper?serverTimezone=UTC";
//
//        String username = "superuser";
//        String password = "SuperDuper123!";
//        var service = ProductServiceFactory.createSqlService(url, username, password);
        //___________________________________________________

        Simulator simulator = new Simulator(service);
        simulator.run(LocalDate.now(), 20);
    }
}