package com.javaremotero69.ex2_bubble_sort;

/*
    Write a program that receives array size (n) from user (System.in). Next, receive n
    numbers from user and put them in the array.

    Implement bubble sort algorithm to sort this array in ascending order.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
        /** Citire */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the list: ");
        int size = scanner.nextInt();

        List<Integer> numbers = new ArrayList<>(size);
        System.out.println("Enter the elements of the list: ");
        for (int i = 0; i < size; i++) {
            int tmp = scanner.nextInt();
            numbers.add(tmp);
        }

        /** Sortare */
//        bubbleSortFor(numbers);
        bubbleSortDoWhile(numbers);

        /** Afisare */
        System.out.println("Sorted list: ");
        for (Integer it : numbers) {
            System.out.print(it + " ");
        }
    }

    /*
     Algoritmul de sortare va dispune de 2 bucle repetitive:
        - prima, va avea ca responsabilitate indexul de start (index care va porni de la 0, pana la penultimul element)
        - a doua, va avea responsabilitatea de a face verificari adiacente IN SUBSIRURILE DETERMINATE de indexul de start
     1st for i = 0, pana la n-1, n = numbers.size()
        2nd for j = 0, pana la n-1-i (punem acel -i, pt a restrange cu 1 la fiecare iteratie sublista nesortata din stanga)
         i = 0, j = 0
            8 1 5 92 4
         i = 0, j = 1
            1 8 5 92 4
         i = 0, j = 2
            1 5 8 92 4 -> 8 >? 92 - nu, deci nu facem swap
         i = 0, j = 3
            1 5 8 92 4 -> 92 >? 4 - da, facem swap
         i = 0, j = 4
            1 5 8 4 92
         -------------
         i = 1, j = 0 -> ne dorim cu fiecare iteratie sa restrictionam sublista in care facem comparatii cu cate 1
                       -> 1 5 8 4, deci lista finala la finalul iteratiei va fi 1 5 4 8 92
         -------------
         i = 2, j = 0 -> 1 5 4, lista finala va fi 1 4 5 8 92
         -------------
         i = 3, j = 0 -> 1 4, lista finala va fi 1 4 5 8 92
         -------------
         done
     1. Parcurgem lista si facem comparatii adiacente de valori.
     2. Pt ordine ascendenta -> elementul din Stanga < elementul din Dreapta
     3. Daca elementele nu sunt in ordinea coreta, le inversam.
      */
    private static void bubbleSortFor(List<Integer> numbers) {
        int n = numbers.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;    // mergem pe premiza ca toate elementele sunt sortate
            for (int j = 0; j < n - 1 - i; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    swapped = true;
                    int tmp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, tmp);

                    // 1 3 2 - tmp = 1 (backup)
                    // 3 3 2
                    // 3 1 2 - folosim backupul
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private static void bubbleSortDoWhile(List<Integer> numbers) {
        int n = numbers.size();
        boolean swapped;

        // todo optimizati prin adaugarea unui contor i astfel incat sa nu faceti bucle repetitive complete cu for-ul
        do {
            swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    swapped = true;
                    int tmp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, tmp);
                }
            }
        } while (swapped);
    }
}
