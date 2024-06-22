package com.example.spocktestimplementation.service.impl;

import com.example.spocktestimplementation.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public long sumOfNumbers(int... nums) {
        return Arrays.stream(nums).asLongStream().sum();
    }

    @Override
    public double divide(double num1, double num2) {
        if (num2 == 0) throw new ArithmeticException("Can not divide by zero");
        return num1 / num2;
    }
}
