package com.pluralsight.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Michael", "Doe", 25));
        people.add(new Person("Elif", "Kalp", 27));
        people.add(new Person("Yusuf", "Uzun", 30));
        people.add(new Person("Can", "Doe", 20));
        people.add(new Person("Zeynep", "Kibar", 31));
        people.add(new Person("Nina", "Miller", 19));
        people.add(new Person("David", "Garcia", 45));
        people.add(new Person("Sibel", "Martinez", 32));
        people.add(new Person("Tom", "Rodriguez", 27));
        people.add(new Person("Jake", "Brown", 35));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name (first or last) to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();

        List<Person>matchPeople = new ArrayList<>();

        for(Person person : people) {
            if (person.getFirstName().equalsIgnoreCase(searchName)) {
                person.getLastName().equalsIgnoreCase(searchName);
                matchPeople.add(person);
            }

        }

        if (matchPeople.isEmpty()) {
            System.out.println("Can not found name with that time");

        } else {
            System.out.println("people matched" + searchName + ":");
            for (Person person : matchPeople) {
                System.out.println(person);
            }
        }
        scanner.close();
    }
}



    

