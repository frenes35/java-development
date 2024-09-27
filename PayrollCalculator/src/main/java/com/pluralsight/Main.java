package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: " );
        String name = input.nextLine();

        System.out.print("Enter hours worked: ");
        double hoursWorked = input.nextDouble();

        System.out.print("Enter pay rate: ");
        double payRate = input.nextDouble();


        double grossPay = hoursWorked * payRate;

        System.out.print("Employee Name and grosspay " + name + "  " + grossPay);








    }
}
