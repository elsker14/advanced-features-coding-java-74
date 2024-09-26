package com.javaremotero69.ex10_interf_func;

/*
    Exercitiu teoretic pentru interfetele functionale
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Interfete normale:
 * definesc un contract de metode pe care mai departe o clasa le va implementa
 * fiecare clasa ce implementeaza interfete va propune o implementare concreta a tuturor metodelor abstracte
 * o interfata poate avea MULTIPLE METODE ABSTRACTE
 * pe langa acestea interfata poate avea si metode default sau statice
 * <p>
 * Clasa abstrata:
 * principala diferenta intre interfete si clase abstracte este faptul ca o interfata poate initializa obiecte
 * pa baza constructorului clasei de implementare, in timp ce o clasa abstracta nu poate
 * <p>
 * scopul principal al claselor abstracte este de a fi mostenite in calitate de clase parinte, ele se comporta ca
 * niste clase de configuratie
 * <p>
 * o alta diferenta e faptul ca o clasa abstracta poate avea atribute, constructori, getters/setters, toString etc
 * <p>
 * Metoda abstracta:
 * ele pot fi declarate atat la nivel de clasa abstracta, cat si la nivel de interfata, fara a avea un corp de implementare
 * de aceea ele se comporta ca o schita contractuala a unei functionalitati
 */
interface Vehicle {

    void start();

    void stop();
}

// 'Airplane' is abstract; cannot be instantiated
abstract class Airplane {

    abstract void start();

    abstract void stop();
}

/**
 * Inteferfete functionale:
 * la baza comportamentul arhitectural e tot de interfata
 * sunt tipuri de clase predefinite in Java, avand ca scop unificarea si standardizarea stilului de a scrie cod
 * interfata functionala DEFINESTE O SINGURA METODA ABSTRACTA
 * de ce? pentru ca e mult mai usor sa avem ceva granular care are o singura responsabilitate, decat ceva mai complex cu
 * mai multe
 * anotatia cu care sunt marcate este @FunctionalInterface
 * <p>
 * interfetele functionale sunt gandite special pentru a fi folosite in contextul expresiilor lambda si a metodelor de
 * referinta (Clasa::method)
 */

public class FunctionalInterfaces {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(); // clasa ArrayList implementeaza interfata List

        /* Tipuri de interfete functionale */

        // 1. Runnable (no arguments, no return value)
        // - similar cu metodele void care nu returneaza nimic, si care pot sa nu aiba argumente
        // public void display() { System.out.println() }
        // este adesea utilizata in paralelizarea codului astfel incat blocul de cod de executie din
        // expresia lambda sa fie rulat pe threaduri diferite
        // programarea paralela = multithreading
        Runnable runnable = () -> {
            // bloc de cod colectiv
            System.out.println("Runnable is running fast!");
        };
        new Thread(runnable).start();

        // 2. Supplier <T> (no arguments, returns a value)
        // - echivalentul lui Supplier la nivel de metoda este de a se comporta ca un GETTER
        // - o diferenta e modul de initializare, static si constant, fata de dinamic
        // - o alta diferenta e gradul de abstractizare, in situatia utilizarii lui Supplier,
        // putem golosi get in orice invocare externa a obiectului pentru a avea acces la valoare,
        // fara posibilitatea de a o altera
        String message = "Hello from primitive! I not am hiding!";
        System.out.println(message);

        Supplier<String> messageSupplier = () -> "Hello from Supplier! I am hiding!";
        System.out.println(messageSupplier.get());

        // 3. Predicate <T> (takes an argument, returns a boolean)
        // este utilizat in definirea instructiunilor conditionale sub forma de expresii lambda
        // de ex: filter() primeste ca si argument un Predicate
        Predicate<Integer> isEven = value -> value % 2 == 0;
        System.out.println("45 este " + (isEven.test(45) ? "par" : "impar"));
        System.out.println("22 este " + (isEven.test(22) ? "par" : "impar"));
        /**
         * public boolean isEven(int value)
         * { return value % 2; }
         *
         * Math.isEven(45)
         * Math.isEven(22)
         *
         * Instructiunea conditionala tertiara e o varianta prescurtata de if cu 2 branchuri.
         * (isEven.test(45) ? "par" : "impar")
         * se traduce
         * if(isEven.test(45)) {
         *   return "par";
         * } else {
         *  return "impar"
         * }
         */

        // 4. Consumer <T> (iteratorul din lambda = takes an argument, no return value)
        Consumer<String> messageConcatenation = str -> {
            System.out.println("Concatenated the following message: " + str);
        };
        messageConcatenation.accept("Hello consumers!");

        // creeaza o lista mutabila, spre deosebire de List.of care crea una imutabile
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        Consumer<List<String>> convertorToUpperCase = namesArg -> {
            for (int i = 0; i < namesArg.size(); i++) {
                namesArg.set(i, namesArg.get(i).toUpperCase());
            }
        };
        /*
        * public void convertorToUpperCase(List<String> namesArg) {
        *   for (int i = 0; i < namesArg.size(); i++) {
                list.set(i, list.get(i).toUpperCase());
            }
        * }
        * */
        convertorToUpperCase.accept(names); // convertorToUpperCase(names);
        System.out.println("UpperCase names: " + names);

        // 5. BiConsumer<T, U> (takes two arguments, no return value)
        // exemplu: afisati un mesaj de un anumit numar de ori
        BiConsumer<String, Long> displayRepeated = (messageToRepeat, nrOfTimes) -> {
            int count = 0;

            while (count < nrOfTimes) {
                System.out.println(messageToRepeat);
                count++;
            }
        };
        displayRepeated.accept("Mesajul va fi repetat!", 5L);
        /*
        * public void displayRepeated(String messageToRepeat, Integer nrOfTimes) {
        *   int count = 0;

            while (count < nrOfTimes) {
                System.out.println(messageToRepeat);
                count++;
            }
        * }
        * */

        // 6. Function  <T, R> (takes an argument, returns a value)
        // exemplu: calculate exponent of 2^exp
        Function<Integer, Double> calculateExponent = exp -> Math.pow(2, exp);
        System.out.println("2^8 = " + calculateExponent.apply(8));
        /*
         *  public Double calculateExponent(Integer exp)
         *  { return Math.pow(2, exp); }
         */

        // 7. BiFunction <T, U, R> (takes two arguments of different types, returns a value)
        // exemplu: build a string of repeated text -> Hi, 3 -> HiHiHi
        BiFunction<String, Integer, String> concatAndMultiply = (strToRepeat, nrOfRepetitions) -> strToRepeat.repeat(nrOfRepetitions);
        System.out.println(concatAndMultiply.apply("Hi", 5));

        BiFunction<Integer, Integer, Integer> sumOfTwoNumbers = (a, b) -> a + b;
        System.out.println(sumOfTwoNumbers.apply(5,6));

        // 8. UnaryOperator <T> (takes one argument, returns a value of the same type)
            // - este o varietate de Function specializata pe metode care au acelasi tip de date de return ca si argumentul dat
        UnaryOperator<String> convertorToLowerCase = word -> word.toLowerCase();   // String::toLowerCase;
        System.out.println(convertorToLowerCase.apply("LA MULTI ANI 2025!"));

        // 9. BinaryOperator <T> (takes two arguments, returns a value of the same type)
            // - este o varietate de BiFunction specializata pe metode care au acelasi tip de date de return ca si argumentele date
        BinaryOperator<Integer> sumOfTwoNumbersVersion2 = (a, b) -> Integer.sum(a, b);  // Integer::sum
        System.out.println(sumOfTwoNumbersVersion2.apply(3,4));
    }
}
