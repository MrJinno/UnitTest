package com.mrJinno.UnitTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StringCalculator {
    private String delimiters;
    private String numbers;
    private List<String> delimiterList;
    private List<String> stringList;
    private List<Integer> negativeNumbersList;

    int add(String numbersString) {
        this.numbers = numbersString;
        if (numbers.isEmpty()) return 0;
        setDelimiters();
        stringList = Arrays.asList(numbers.split(delimiters));
        removeBlankSpotsFromStringList();
        List<Integer> numberList = createNumberListUnder1000(stringList);
        if (handleNegativeNumbers(numberList)) throwNegativeNumberException();
        return numberList.stream()
                .mapToInt(number -> number)
                .sum();
    }

    private void setDelimiters() {
        delimiters = "[,\n]";
        if (checkDelimiterInitializer()) {
            if (checkDelimiterBrackets()) {
                addDelimitersToDelimitersList();
                delimiters = setRegexString();
            } else {
                delimiters = numbers.substring(2, 3);
                numbers = numbers.substring(4);
            }
        }
    }

    private boolean checkDelimiterInitializer() {
        if (numbers.length() > 1) {
            return numbers.substring(0, 2).equals("//");
        }
        return false;
    }

    private boolean checkDelimiterBrackets() {
        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == '[') return true;
        }
        return false;
    }

    private void addDelimitersToDelimitersList() {
        delimiterList = new ArrayList<>();
        int startIndex;
        int endIndex = 0;
        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == '[') {
                startIndex = i + 1;
                for (int j = i; j < numbers.length(); j++) {
                    if (numbers.charAt(j) == ']') {
                        endIndex = j;
                        delimiterList.add(numbers.substring(startIndex, endIndex));
                        break;
                    }
                }
            }
        }
        numbers = numbers.substring(endIndex + 2);
    }

    private String setRegexString() {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("[");
        for (String s : delimiterList) {
            regexBuilder.append(s);
        }
        regexBuilder.append("\n");
        regexBuilder.append("]");
        return regexBuilder.toString();
    }
    
    private void removeBlankSpotsFromStringList() {
        stringList= stringList.stream()
                .filter(string->!string.isEmpty())
                .collect(Collectors.toList());
    }

    private List<Integer> createNumberListUnder1000(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt)
                .filter(number -> number <= 1000)
                .collect(Collectors.toList());
    }

    private boolean handleNegativeNumbers(List<Integer> numberList) {
        int negativeNumbersCount = 0;
        negativeNumbersList = new ArrayList<>();
        for (int number : numberList) {
            if (number < 0) {
                negativeNumbersList.add(number);
                negativeNumbersCount++;
            }
        }
        return negativeNumbersCount > 0;
    }

    private void throwNegativeNumberException() {
        throw new IllegalArgumentException("Numbers must be higher than 0: " + negativeNumbersList);
    }

    public List<Integer> getNegativeNumbersList() {
        return negativeNumbersList;
    }
}
