package com.javaremotero69.ex7_first_in_list;

/*
    Create multiple instances of the Employee class and add them to a list. Using
    iterative methods or streams, find first Employee that is not null and has salary of at least 30000.
    If not found, throw exception.

    Req:
        - create Employee object class
            -- 2 private attributes: name, salary
            -- constructor, getters/setters, toString
            -- extra (?)

        - in main create a List<Employee> with multiple instances
            -- fie adaugati angajatii in lista folosint List.of
            -- fie creati lista goala si folositi metoda ".add(Employee obj)"
            -- fie creati lista goala si cititi repetitiv de la tastatura atributele necesare, construiti obiectul,
            il adaugati in clasa
        - find first Employee that is not null and has salary of at least 30000
            -- metoda iterativa
            -- metoda streamuri
            -- ambele metode returneaza un obiect de tipul Employee, pe care il verificam daca e null sau nu
            (Optional<Employee> ?)
        - if not found, throw exception or print a message
            -- fie aruncati o exceptie daca obiectul e null
            -- fie printati un mesaj cu SOUT

    Input:
        - 3 sau 4 obiecte de tipul Employee caracterizate de un constructor parametrizat -> new Employee(<nume>, <salariu>)
        - obiectele se adauga in lista dupa metoda preferata

    Output: pot fi 3 situatii
        - un singur emoployee cu salariu > 30000
            -- daca sunt mai multi, fie maximul, cel cu salariul cel mai mare
            -- fie oricare, primul in ordinea citirii
        - un mesaj -> "Nu e niciun angajat cu salariul specificat"
        - o exceptie -> "Nu e niciun angajat cu salariul specificat"
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Solutie propusa de Eduard */

public class FirstInList {
    public static void main(String[] args) {

        List<Employee> listEmployee= new ArrayList<>( Arrays.asList(
                new Employee("Jhon", 15000 ),
                new Employee("Marius", 20000 ),
                new Employee("Gigi", 30000 ),
                new Employee("Remus", 35000 )));

        System.out.println("Angajatul care are cel mai mare salariu este: " + MaxSalaryMethodStream(listEmployee));
        System.out.println("Angajatul care are cel mai mare salariu este: " + MaxSalaryMethodFor(listEmployee).get(0));
    }

    private static List<Employee> MaxSalaryMethodFor(List<Employee> listEmployee) {

        List<Employee> filteredList = new ArrayList<>();

        for (Employee employee : listEmployee) {
            if(employee.getSalary() >= 30000 ){
                filteredList.add(employee);
            }
        }
        if(filteredList.isEmpty()){
            System.out.println("Nu exista angajati cu salariu mai mare de 30000");
        }

        filteredList.sort(Comparator.comparing(Employee::getSalary).reversed());

        return filteredList;
    }

    private static List<Employee> MaxSalaryMethodStream(List<Employee> listEmployee) {
        List<Employee> filteredList=listEmployee.stream().
                filter(it -> it.getSalary() >= 30000)
                .max(Comparator.comparing(Employee::getSalary))
                .stream()
                .toList();

        if (filteredList.isEmpty()){
            System.out.println("Nu exista angajati cu salariu mai mare de 30000");
        }

        return filteredList;
    }
}




