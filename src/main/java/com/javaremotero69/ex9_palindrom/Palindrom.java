package com.javaremotero69.ex9_palindrom;

/*
    Write a program that receives a list of strings from the user.
    Implement a method to check and display whether each string in the list is a palindrome
    (reads the same forwards and backwards) using streams.

    Req:
        - o lista de stringuri

    Input:
        - List<String> = {"apa", "cana", "mama", "racecar"}

    Output:
        - List<String> = {"apa", "racecar"}

    Metode:
        - iterativ:
            for si if - rezultatele le adaugati intr o lista
        - stream:
            stream si filter -> toList
        -- creati o metoda booleana care verifica ca un string e palindrom
 */

import java.util.ArrayList;
import java.util.List;

public class Palindrom {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("apa", "cana", "mama", "racecar"));

        System.out.println("Elementele sunt palindroame: " + palindromMethodFor(list));
        System.out.println("Elementele sunt palindroame: " + palindromMethodStream(list));
    }

    private static List<String> palindromMethodFor(List<String> list) {
        List<String> palindroms = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String currentElement = list.get(i);
            if (isPalindrome2(currentElement)) {
                palindroms.add(currentElement);
            }
        }
        return palindroms;
    }

    private static List<String> palindromMethodStream(List<String> list) {
        return list.stream()
                .filter(it -> IsPalindrom(it))
                .toList();
    }

    private static boolean IsPalindrom(String element) {
        String reversed = new StringBuilder(element).reverse().toString();
        return element.equals(reversed);
    }

    public static boolean isPalindrome2(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // rac e car
    // i = 0 -> r si j = 6 -> r
    // i = 1 -> a si j = 5 -> a
    // i = 2 -> c si j = 4 -> c
    // i = 3 -> e si j = 3 -> 3
}
