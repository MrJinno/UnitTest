package com.mrJinno.UnitTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {
    private StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyStringShouldReturn0() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void stringOfNumberShouldReturnIt() {
        assertEquals(8, calculator.add("8"));
    }

    @Test
    public void stringOfTwoNumbersShouldReturnTheirSum() {
        assertEquals(9, calculator.add("4,5"));
    }

    @Test
    public void stringOfMoreThanTwoNumbersShouldReturnTheirSum() {
        assertEquals(31, calculator.add("5,10,12,4"));
    }

    @Test
    public void stringOfNumbersWithinManyLinesShouldReturnTheirSum() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void stringOfNumbersWithNewSeparatorShouldReturnTheirSum() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    public void stringOfNegativeNumbersShouldThrowIllegalArgumentException() {
        try {
            calculator.add("-2,3");
        }catch (IllegalArgumentException e){
            String expectedMessage = "Numbers must be higher than 0: " + calculator.getNegativeNumbersList();
            assertEquals(expectedMessage, e.getMessage());
        } catch (Exception ex){
            fail(String.format("Excepted IllegalArgumentException, but %s was thrown", ex.getClass().getName()));
        }
    }

    @Test
    public void stringOfNumbersHigherThan1000ShouldBeIgnored() {
        assertEquals(2, calculator.add("1001,2"));
    }

    @Test
    public void separatorWithMoreThanOneCharShouldReturnSumOfNumbersInString() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void twoNewSeparatorsShouldReturnSumOfNumbersInString() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void manySeparatorsWithMoreThanOneCharShouldReturnSumOfNumbersInString() {
        assertEquals(10, calculator.add("//[***][%%]\n1***2***3%%4\n0"));
    }
}
