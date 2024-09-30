package com.javaremotero69.ex8_first_in_list_sorted;

import lombok.*;

/*
    Mentiuni onorabile:
        - @RequiredArgsConstructor
        - @Data
        - @EqualsAndHashCode
        - @Log (oricare anotatie)
 */

@AllArgsConstructor // constructor parametrizat pentru toate atributele
@NoArgsConstructor  // constructor neparametrizat
@Getter // construieste toate getterele
@Setter // construieste toate setterele
@ToString   // construieste toString-ul
public class EmployeeSorted implements Comparable<EmployeeSorted> {

    private String name;
    private Integer salary;

    /*
        Comparable va sorta AUTOMAT colectia de valori pe parcurs ce adaugam,
        obiectele in lista.
     */
    @Override
    public int compareTo(EmployeeSorted o) {
        // ordine descrescatoare -> Double.compare(o.getSalary(), this.salary);
        // ordine crescatoare -> Double.compare(this.salary, o.getSalary());
        return Double.compare(o.getSalary(), this.salary);
    }
}
