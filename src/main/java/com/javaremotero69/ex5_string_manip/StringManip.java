package com.javaremotero69.ex5_string_manip;

/*
    Given a list of strings, write a method that returns a list of all strings that start with the
    letter 'a' (lower case) and have exactly 3 letters

    Req:
        - strings that start with the letter 'a'
        - have exactly 3 letters

    Input: ana, are, mere, alune, banane, cai, si, boi
    Output: ana, are

    Metoda 1 - iterativ cu for
    Metoda 2 - stream
*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringManip {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("ana", "are", "mere", "alune", "banane", "cai", "si", "boi"));

        System.out.println(methodWithFor(words));
        System.out.println(methodWithStreamFilter(words));
        System.out.println(methodWithStreamCollectors(words));
    }

    private static List<String> methodWithStreamCollectors(List<String> words) {
        return words
                .stream()
                .collect(Collectors.filtering(it -> it.startsWith("a") && it.length() == 3, Collectors.toList()));
    }

    private static List<String> methodWithStreamFilter(List<String> words) {
        return words
                .stream()
                .filter(it -> it.startsWith("a") && it.length() == 3)
                .toList();
    }

    private static List<String> methodWithFor(List<String> words) {
        char letter = 'a';
        List<String> result = new ArrayList<>();

        for (String currentWord : words) {

            if (currentWord.startsWith(String.valueOf(letter)) && currentWord.length() == 3) {
                result.add(currentWord);
            }
        }
        return result;
    }
}
