/*Arithmetic Operators in Java
Write a program that takes two numbers as input from the user and performs arithmetic operations
on them using the arithmetic operators (sum, difference, product, quotient, remainder) in Java. */

import java.util.Scanner;

public class ArithmeticOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        // TODO: Write your code here
        int sum = n1 + n2;
        int diff = n1 - n2;
        int prod = n1 * n2;
        int quot = n1 / n2;
        int rem = n1 % n2;

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + prod);
        System.out.println("Quotient: " + quot);
        System.out.println("Remainder: " + rem);
        scanner.close();
    }
}
