package com.pluralsight;

import java.sql.SQLOutput;

public class MathApp {
    public static void main(String[] args) {
        int bobSalary = 4000;
        int garySalary = 5500;
        int highSalary = Math.max(bobSalary, garySalary);
        System.out.println(highSalary);

        int carprice = 14500;
        int truckprice = 16532;
        int lowestprice = Math.min(carprice, truckprice);
        System.out.println("The Lowest price is " + lowestprice);

        double radius = 7.25;
        double areaofcircle = Math.PI * (radius * radius);
        System.out.println("result:" + areaofcircle);

        double number = 5.0;
        double route = Math.sqrt(number);
        System.out.println("route is " + route);

        int x1 = 5, y1 = 10, x2 = 85, y2 = 50;
        double distance =  Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("The distance between the points is " + distance);

        double num2 = -3.8;
        double absNum2 = Math.abs(num2);
        System.out.println("The absolute value of " + num2 + " is " + absNum2);

        double randomNum = Math.random();
        System.out.println("The random number is " + randomNum);


        }







    }


