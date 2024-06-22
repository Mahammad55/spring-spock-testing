package com.example.spocktestimplementation;

import com.example.spocktestimplementation.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpockTestImplementationApplication implements CommandLineRunner {
    @Autowired
    private CalculatorService calculatorService;

    public static void main(String[] args) {
        SpringApplication.run(SpockTestImplementationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(calculatorService.sumOfNumbers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
