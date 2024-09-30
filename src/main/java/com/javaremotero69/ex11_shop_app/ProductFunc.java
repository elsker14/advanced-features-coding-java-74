package com.javaremotero69.ex11_shop_app;

/*
    Interfata ProductFunc are scopul de a stoca o serie de functionalitati pe care
    GenericProduct le va implementa.
    - getPrice()
    - isAvailable()

    Definim functionalitati generice ce vor fi suprascrise in clasele de implementare,
    aplicand logica interfetelor functionale definite la nivel de atribute.
 */

import java.time.LocalDate;

public interface ProductFunc {

    String getName();

    double getPrice();

    boolean isAvailable(LocalDate productDate);

    String toStringByDate(LocalDate productDate);
}
