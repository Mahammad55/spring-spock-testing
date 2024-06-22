package com.example.spocktestimplementation.service

import com.example.spocktestimplementation.service.impl.CalculatorServiceImpl
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CalculatorServiceTest extends Specification {
    @Subject
    private CalculatorServiceImpl calculatorService = new CalculatorServiceImpl()

    def "Test1SumOfNumbers success case"() {
        given:
        def nums = new int[]{1, 2, 3}

        when:
        def result = calculatorService.sumOfNumbers(nums)

        then:
        result == 6
    }

    def "Test2SumOfNumbers success case"() {
        when:
        def result = calculatorService.sumOfNumbers(nums)

        then:
        result == expectedResult

        where:
        nums                | expectedResult
        new int[]{1, 2, 10} | 13
        new int[]{}         | 0
        new int[]{-8, -15}  | -23
    }

    @Unroll
    def "TestDivide success case"() {
        given:
        def num1 = 55
        def num2 = 10

        when:
        def result = calculatorService.divide(num1, num2)

        then:
        result == 5.5
    }

    def "TestDivide fail case"() {
        given:
        def num1 = 55
        def num2 = 0

        when:
        calculatorService.divide(num1, num2)

        then:
        def exception = thrown(ArithmeticException)
        exception.message == "Can not divide by zero"
    }
}
