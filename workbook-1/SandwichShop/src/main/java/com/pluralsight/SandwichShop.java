package com.pluralsight;

import java.util.Scanner;

public class SandwichShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Select the sandwich size:");
        System.out.println("1: Regular (Base price: $5.45)");
        System.out.println("2: Large (Base price: $8.95)");
        double regularSandwich = 5.45;
        double largeSandwich = 8.95;
        double firstChoose = scanner.nextDouble();


        System.out.println("How old are you?");
        int customerAge =scanner.nextInt();
        if (customerAge <= 17) {
            System.out.println("%10 discount.");

        }else if ( customerAge >= 65) {
            System.out.println("%20 disount.");

        }else  {
            System.out.println("There is no any discount.");
        }

        double newPrice = 5.45 - (5.45 * 0.10);
        double newPrice2 = 8.95 - (8.95 * 0.20);
        double newPrice3 = 5.45 - (5.45 * 0.10);
        double newPrice4 =  8.95 - (8.95 * 0.20);

        if (firstChoose == 1) {
            if (customerAge <= 17){
                System.out.println("Your price is 8.95 but you got %10 discount, total price is: ");
            }
        }
    }
}