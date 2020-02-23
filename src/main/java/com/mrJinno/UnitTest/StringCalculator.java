package com.mrJinno.UnitTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StringCalculator {
    private String regex;
    private String numbers;
    private List<String> delimiterList;
    private List<String> stringList;

    int add(String numbersString) {
        this.numbers = numbersString;
        if (numbers.isEmpty()) return 0;
        setDelimiters();
        stringList = Arrays.asList(numbers.split(regex));
        removeBlankSpotsFromStringList();
        List<Integer> numberList = createNumberListUnder1000(stringList);
        lookForNegativeNumbers(numberList);
        return numberList.stream()
                .mapToInt(number -> number)
                .sum();
    }

    private void setDelimiters() {
        regex = "[,\n]";
        if (checkDelimiterInitializer()) {
            if (checkBrackets()) {
                addDelimitersToDelimitersList();
                regex = setRegexString();

            } else {
                regex = numbers.substring(2, 3);
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

    private boolean checkBrackets() {
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
        List<String> tempList = new ArrayList<>();
        for (String s : stringList) {
            if (!s.isEmpty()) {
                tempList.add(s);
            }
        }
        stringList = new ArrayList<>(tempList);
    }

    private List<Integer> createNumberListUnder1000(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt)
                .filter(number -> number <= 1000)
                .collect(Collectors.toList());
    }

    private void lookForNegativeNumbers(List<Integer> numberList) {
        int minusNumbersCount = 0;
        List<Integer> wrongNumbersList = new ArrayList<>();
        for (int number : numberList) {
            if (number < 0) {
                wrongNumbersList.add(number);
                minusNumbersCount++;
            }
        }
        if (minusNumbersCount > 0)
            throw new IllegalArgumentException("Numbers must be higher than 0: " + wrongNumbersList);
    }
}
