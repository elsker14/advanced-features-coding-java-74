package com.javaremotero69.ex11_shop_app;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

/* ProductFunc -> GenericProduct -> ShoppingCart
 * GenericProduct implementeaza ProductFunc folosind interfete functionale generice
 * ShoppingCart se compune cu GenericProduct, deoarece stocheaza la nivel de atribute o lista de acea natura
 * */

@AllArgsConstructor
@ToString
public class GenericProduct implements ProductFunc {
    /*
    private Double price;
    private LocalDate date;

    In situatia de fata in care am utiliza 2 atribute clasice, am fi nevoiti sa expunem
    toata logica specifica a lui price/availability in cele 2 metode getPrice si isAvailable.

    In situatia in care avem mai multe tipuri de produse, cele 2 metode, trebuie sa aiba cate o implementare
    pentru fiecare situatie.

    In timp ce utilizand interfete functionale, putem avea o singura implementare pentru toate situatiile,
    deoarece generalizarea se face la nivel de obiect cand definim expresiile lambda.
    */

    private Supplier<Double> price;
    private final Function<LocalDate, Boolean> availability;
    private final Supplier<String> name;
    // todo: T, T = o clasa specifica de produs cu atribute proprii

    @Override
    public String getName() {
        return this.name.get();
    }

    @Override
    public double getPrice() {
        return this.price.get();
    }

    public void setPrice(Double value) {
        this.price = () -> value;
    }

    @Override
    public boolean isAvailable(LocalDate productDate) {
        return this.availability.apply(productDate);
    }

    @Override
    public String toStringByDate(LocalDate productDate) {
        boolean isAvailableOnDate = this.isAvailable(productDate);

        return "GenericProduct{" +
                "price=" + this.getPrice() +
                ", availability=" + (isAvailableOnDate ? "Available on " + productDate : "Unavailable on " + productDate) +
                ", name='" + this.getName() + '\'' +
                '}';
    }
}
