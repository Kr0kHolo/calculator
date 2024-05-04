package main;

import java.util.Scanner;

public class Main {
    static private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter an expression: ");
        System.out.println(calc(sc.nextLine()));
        sc.close();
    }

    public static String calc(String expression){
        Calculator clc = new Calculator();
        return clc.work(expression);
    }
}
