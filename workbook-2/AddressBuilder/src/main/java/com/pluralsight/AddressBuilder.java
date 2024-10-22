package com.pluralsight;

import java.util.Scanner;

public class AddressBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder addresBuilder = new StringBuilder();

        System.out.print("Full name: ");
        String fullname = scanner.nextLine();
        addresBuilder.append(fullname).append("\n");

        System.out.print("Billing Street: ");
        String billingStreet = scanner.nextLine();
        System.out.print("Billing City: ");
        String billingCity = scanner.nextLine();
        System.out.print("");


    }


}
