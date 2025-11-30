package org.example;

import org.example.service.ProductServiceFactory;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Run with hardcoded data
        //var service = ProductServiceFactory.createSampleService();

        // Run with data received from CSV file ''products.csv''
        var service = ProductServiceFactory.createCsvService("products.csv");


        Simulator simulator = new Simulator(service);
        simulator.run(LocalDate.now(), 15);
    }
}