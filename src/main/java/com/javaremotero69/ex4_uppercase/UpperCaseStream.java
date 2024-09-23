package com.javaremotero69.ex4_uppercase;

/*
    Write a method that converts all strings in a list to their upper case using streams.

    Input: ana, are, mere
    Output: ANA, ARE, MERE

    Metoda 1:
        - stream
        - toUpperCase per fiecare element
        - map

    Metoda 2:
        - for
        - toUpperCase per fiecare element
        - get element pe pozitia i, set cu upperCase

    Metoda 3:
        - extra, uitati va la functionalitatile din String

*/

import java.util.List;

public class UpperCaseStream {

    public static void main(String[] args) {
        List<String> texts = List.of("ana", "are", "mere");
    }
}
