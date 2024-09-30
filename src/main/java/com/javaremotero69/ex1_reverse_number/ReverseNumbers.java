package com.javaremotero69.ex1_reverse_number;

/*
    Reverse a number using while Loop.
    The program will prompt user to input the number,
    and then it will reverse the same number using while loop.

    8765 -> 5678

    Metoda 1: 8765 = 8000+700+60+5=5 cu restul impartirii la 10
     while(???) {

    }

    Metoda 2:
    8765 -> ? -> array?
    array = {8, 7, 6, 5}
    parcurs invers

    Metoda 3:
    8765 -> toString -> List<Character>


    Ce e complexitatea? De ce alegem varianta 1 in locul lui 2 si 3?
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        System.out.println("Solution 1: " + getReverseNumberM1(number));
        System.out.println("Solution 2: " + getReverseNumberM2(number));
        System.out.println("Solution 3: " + getReverseNumberM3(number));
    }

    private static int getReverseNumberM3(int number) {
        int reversedNumber = 0;
        String reversedStr = "";    // empty string e echivalentul lui 0 la numere intregi
        String numberStr = Integer.toString(number);
        int index = numberStr.length() - 1;  // genul acesta de metode e utilizat pentru conversia tipului de date intre Wrappere
        /*
            Salvam lungimea sirului de caractere pentru ca este relevanta in determinarea indexului ultimului caracter.
            Sirul de caractere, cunoscut sub numele de String, are indexare de la 0.
            Prin urmare:
            poz 0 - 8
            poz 1 - 7
            poz 2 - 6
            poz 3 - 5

            metoda length intoarce lungimea totala a sirului = 4
            index = length - 1 = 3
         */
        // Primul while asigura o repetitie completa -> O(n)
        while(index >= 0) {
            // Concatenarea in Java e o procedura de creare iterativa a unui sir de caractere -> O(n)
            reversedStr = reversedStr + numberStr.charAt(index);
            index--;
        }
        // Complexitatea totala este O(n) * O(n) = O(n^2)

        reversedNumber = Integer.parseInt(reversedStr); // conversie de la String la int
        return reversedNumber;
    }

    private static int getReverseNumberM2(int number) {
        List<Integer> numberDigits = new ArrayList<>();
        int reversedNumber = 0;

        // O(n) -> de ce? pentru ca facem o repetitie completa de la 0 la number.size - 1 = n
        for (int i = 0; number > 0; i++) {
            numberDigits.add(number % 10);
            number /= 10;
        }
        System.out.println("Number Digits: " + numberDigits); // [5, 6, 7, 8]

        // O(n) -> parcurgem in intregime lista de la 0 la numberDigits.size() - 1
        for(Integer it: numberDigits) {
            reversedNumber = reversedNumber * 10 + it;
        }

        // Complexitatea totala se calculeaza prin adunare: O(n) + O(n) = 2*O(n) = O(n)

        return reversedNumber;
    }

    private static int getReverseNumberM1(int number) {
        /*
        number = 8765
        while(number > 0) {
            - extragem restul cu number%10              -> 5
            - eliminam cifra unitatilor cu number/10    -> 876
            - reconstructia numarului                   -> reverseNumber*10 + rest

        }
         */
        int reverseNumber = 0;  // O asignare de valoare are complexitate liniara O(1)

        // O repetitie care se parcurge de n ori, n = number.length()
        // Pentru ca facem o repetitie completa de la 0 la n,
        // adica parcurgem toate cifrele numarului, complexitatea este O(n)
        while (number > 0) {
            int rest = number % 10;
            number = number / 10;   // shortcut -> number /= 10;
            reverseNumber = reverseNumber * 10 + rest;
        }

        /*
            I:
                8765 % 10 = 5
                8765 / 10 = 876
                rN = 0 * 10 + 5 = 5

            II:
                876 % 10 = 6
                876 / 10 = 87
                rN = 5*10 + 6 = 56
            ...
         */

        return reverseNumber;
    }

    /* Daca numarul are zerouri in extremitati? 1990
    S1: reconstituire inversa sub forma de String -> seamana cu M3 alterat
    S2: salvarea cifrelor intr-o lista si parcurgerea ei, DAR in modul asta nu vom avea o valoare individuala -> seamana cu M2 alterat
    * */

    // todo: aplicati acelasi algoritm de reconstructie de numere intregi (similar cu M1) in mod RECURSIV
    // todo: aplicati notiunile de streamuri pentru prelucrarea inversa a cifrelor si reductia rezultatului final intr-un Integer
}
