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
    public void string8ShouldReturn8() {
        assertEquals(8, calculator.add("8"));
    }

    @Test
    public void string4And5ShouldReturn9() {
        assertEquals(9, calculator.add("4,5"));
    }

    @Test
    public void string5And10And12And4ShouldReturn31() {
        assertEquals(31, calculator.add("5,10,12,4"));
    }

    @Test
    public void string1AddedToNextLineString2And3ShouldReturn6() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void stringOfNumbers1And2SeparatedByNewDecimalShouldReturn3() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringOfNumbersMinus2and3ShouldThrowIllegalArgumentException() {
        calculator.add("-2,3");
    }

    @Test
    public void stringOfNumbers1001And2ShouldReturn2() {
        assertEquals(2, calculator.add("1001,2"));
    }

    @Test
    public void delimiterWithMoreThanOneCharShouldWork() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void twoDelimitersShouldWork() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void manyDelimitersWithMoreThanOneCharShouldWork() {
        assertEquals(10, calculator.add("//[***][%%]\n1***2***3%%4\n0"));
    }
}
