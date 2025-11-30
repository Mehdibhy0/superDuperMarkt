package org.example;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        var service = Simulator.createSampleService();
        var service = Simulator.createCsvService("products.csv");

        Simulator simulator = new Simulator(service);

        simulator.run(LocalDate.now(),15);
    }
}