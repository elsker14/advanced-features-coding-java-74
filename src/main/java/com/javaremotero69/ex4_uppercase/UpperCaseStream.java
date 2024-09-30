package com.javaremotero69.ex4_uppercase;

/*
    Write a method that converts all strings in a list to their upper case using streams.

    Input: ana, are, mere
    Output: ANA, ARE, MERE

    Metoda 1:
        - stream
        - map
        - toUpperCase per fiecare element

    Metoda 2:
        - for
        - toUpperCase per fiecare element
        - get element pe pozitia i, set cu upperCase

    Metoda 3:
        - extra, uitati va la functionalitatile din String
        - StringBuilder

    Metoda 4:

*/

import java.util.ArrayList;
import java.util.List;

public class UpperCaseStream {

    public static void main(String[] args) {
        List<String> texts = new ArrayList<>(List.of("ana", "are", "mere"));
        displayTexts(texts);

        List<String> textsUpper1 = convertStringListToUpperCaseFor(texts);
        displayTexts(textsUpper1);

        List<String> textsUpper2 = convertStringListToUpperCaseStream(texts);
        displayTexts(textsUpper2);

        System.out.println(convertStringListToStringBuilder(texts));

        /*
            keywordul final ne permite sa cream portiuni de cod CONSTANTE

            cand o colectie de valori/elemente/obiecte este constanta, se numeste IMUTABILA
            cand o colectie este variabila, se numeste MUTABILA

            in momentul in care aveti metode void, si pasati anumite argumente din main in aceasta
            toate modificarile asupra lor in metoda se vor propaga inapoi in main
            de ce? pentru ca argumentul va crea o copie a referintei in memorie, si obiectul din argument
            va coexista in 2 locuri
                - odata in main
                - odata in metoda
            prin urmare, ce prelucrari se vor intampla in metoda, vor aparea si in main
         */
        convertStringListToUpperCaseReplace(texts);
        displayTexts(texts);
    }

    private static void convertStringListToUpperCaseReplace(List<String> texts) {
        texts.replaceAll(it -> it.toUpperCase());   // String::toUpperCase
    }

    private static void displayTexts(List<String> listOfTexts) {
        for(String it: listOfTexts) {
            System.out.print(it + " ");
        }
        System.out.println();
    }

    private static List<String> convertStringListToUpperCaseStream(List<String> texts) {
        return texts
                .stream()
                .map(it -> it.toUpperCase())    // String::toUpperCase()
                .toList();
    }

    private static List<String> convertStringListToUpperCaseFor(List<String> texts) {
        List<String> result = new ArrayList<>();

        for(String it: texts) {
            result.add(it.toUpperCase());
        }

        return result;
    }

    private static String convertStringListToStringBuilder(List<String> text) {
        StringBuilder textUpper = new StringBuilder(" ");

        for (String s : text) {
            textUpper.append(s.toUpperCase()).append(" ");

        }
        return textUpper.toString().trim();    // metoda toString() se comporta ca un getter pentru StringBuilder care e un wrapper peste String
    }
}
