package com.mrJinno.UnitTest;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        StringCalculator calculator=new StringCalculator();
        System.out.println(calculator.add("//[***][%%]\n1***2***3%%4%%0"));
        System.out.println(calculator.add("//[*][%]\n1*2%3"));
        System.out.println(calculator.add("5,10,12,4"));

    }
}
