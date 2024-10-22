package com.pluralsight;

import java.util.Scanner;

public class FullNameApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your name\nFirst name: ");
        String firstname = scanner.nextLine().trim();

        System.out.print("Middle name: ");
        String middleName = scanner.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Suffix: ");
        String suffix = scanner.nextLine().trim();

        String fullname = firstname;



        if (!middleName.isEmpty()) {
            fullname = fullname + " " + middleName;


        }



        if (!lastName.isEmpty()) {
            fullname = fullname + " " + lastName;

        }
        if (!suffix.isEmpty()) {
            fullname = fullname + " " + suffix;

        }



        System.out.print("Fullname:" + fullname);






    }
}
