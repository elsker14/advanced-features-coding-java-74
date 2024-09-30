package com.javaremotero69.ex11_shop_app;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductMain {

    /*

     */

    public static void main(String[] args) {
        /* Obiecte de tipul GenericProduct din diverse categorii */
        GenericProduct ruj = new GenericProduct(
                () -> 53.0,
                date -> date.isBefore(LocalDate.now().plusDays(80)),
                () -> "MaxFactor"
        );
        GenericProduct hartieIgienica = new GenericProduct(
                () -> 90.0,
                date -> true,
                () -> "Zewa"
        );
        GenericProduct sprayDeTantari = new GenericProduct(
                () -> 8.4,
                date -> date.isBefore(LocalDate.now().plusMonths(10)),
                () -> "Raid"
        );
        GenericProduct iPhone16ProMax = new GenericProduct(
                () -> 5500.0,
                date -> true,
                () -> "iPhone"
        );

        /* Initilizarea cosului de cumparaturi */
        ShoppingCart cosDeCumparaturi = new ShoppingCart();
        cosDeCumparaturi.addToCart(ruj);
        cosDeCumparaturi.addToCart(hartieIgienica);
        cosDeCumparaturi.addToCart(sprayDeTantari);
        cosDeCumparaturi.addToCart(iPhone16ProMax);

        /* Afisare produse */
        System.out.println("Produsele mele: ");
        display(cosDeCumparaturi);

        /* Afisare produse in functie de o data */
        System.out.println("Produse valabile la o data anume: ");
        LocalDate checkDate = LocalDate.of(2025, 2, 15);
        cosDeCumparaturi.getSelectedProducts().forEach(
                it -> System.out.println(it.toStringByDate(checkDate))
        );
        System.out.println();

        // 1*. O varianta de filtrare si afisare pt ex1
        System.out.println("1*. Produsele valabile la data de: ");
        List<GenericProduct> result = filterByDate(cosDeCumparaturi, checkDate);
        result.forEach(
                it -> System.out.println(it.toStringByDate(checkDate))
        );
        System.out.println();

        // 1. Creati o sublista de produse filtrate pe baza unei date calendaristice fie data curenta, fie o data aleasa
        System.out.println("1. Produse valabile: ");
        display(filterByDate(cosDeCumparaturi, LocalDate.now()));

        // 2. Calculati pretul total al produselor
        System.out.println("2. Pretul total este: " + getTotalPrice(cosDeCumparaturi) + "\n");

        // 3. Mapati un nou pret pentru toate produsele in functie de un discount dat
        System.out.println("3. Produsele dupa discountul de 10%:");
        mapDiscount(cosDeCumparaturi, 10).forEach(
                it -> System.out.println(it.toStringByDate(checkDate))
        );
        System.out.println();

        // 4. Identificati cel mai scump produs (max/filter)
        System.out.println("4. Cel mai scump produs: " + getMostExpensiveProduct(cosDeCumparaturi) + "\n");

        // 5. Identificati cel mai ieftin produs
        System.out.println("5. Cel mai ieftin produs: " + getMostCheapestProduct(cosDeCumparaturi) + "\n");

        // 6. Extrageti intr-o sublista doar preturile"
        System.out.println("6. Lista preturilor este: " + getPrices(cosDeCumparaturi) + "\n");

        // 7. Creati o mapa a tuturor produselor in functie de pret si valabilitate -> Map<Double, Boolean> + afisare
        System.out.println("7. Mapa produselor in functie de pret si valabilitate: ");
        mapPriceToAvailability(cosDeCumparaturi, checkDate).forEach(
                (price, availability) -> System.out.println("Price: " + price + ", Available: " + availability)
        );
        System.out.println();
    }

    // metode pentru exercitii
    private static void display(ShoppingCart cart) {
        cart.getSelectedProducts().forEach(System.out::println);
        System.out.println();
    }

    private static void display(List<GenericProduct> list) {
        list.forEach(System.out::println);
        System.out.println();
    }

    private static List<GenericProduct> filterByDate(ShoppingCart cart, LocalDate date) {
        return cart.getSelectedProducts()
                .stream()
                .filter(product -> product.isAvailable(date))
                .collect(Collectors.toList());
    }

    private static Double getTotalPrice(ShoppingCart cart) {
        return cart.getSelectedProducts()
                .stream()
                .mapToDouble(GenericProduct::getPrice)
                .sum();
    }

    private static List<GenericProduct> mapDiscount(ShoppingCart cart, int discount) {
        return cart.getSelectedProducts()
                .stream()
                .peek(product -> product.setPrice(product.getPrice() * (100 - discount) / 100))
                .toList();
    }

    private static GenericProduct getMostExpensiveProduct(ShoppingCart cart) {
        return cart.getSelectedProducts()
                .stream()
                .max(Comparator.comparingDouble(GenericProduct::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No products available"));
    }

    private static GenericProduct getMostCheapestProduct(ShoppingCart cart) {
        return cart.getSelectedProducts()
                .stream()
                .min(Comparator.comparingDouble(GenericProduct::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No products available"));
    }

    private static List<Double> getPrices(ShoppingCart cart) {
        return cart.getSelectedProducts()
                .stream()
                .map(GenericProduct::getPrice)
                .collect(Collectors.toList());
    }

    private static Map<Double, Boolean> mapPriceToAvailability(ShoppingCart cart, LocalDate date) {
        return cart.getSelectedProducts()
                .stream()
                .collect(Collectors.toMap(GenericProduct::getPrice, product -> product.isAvailable(date)));
    }
}
