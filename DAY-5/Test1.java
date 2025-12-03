/*Exception hanlding in java
Write a Java program that takes two integers as input and performs division. 
The program should handle exceptions for invalid input (non-integer input) and 
division by zero. The program should continue to prompt the user for valid input 
until successful division is performed. */

import java.util.InputMismatchException;
import java.util.Scanner;

class Test1 {
    public static void main(String args[] ) throws Exception {
        
        // Write your code here
        Scanner sc = new Scanner(System.in);
        int n1 = 0, n2 = 0;

        while(true) {
            try {
                n1 = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }

        while(true) {
            try {
                n2 = sc.nextInt();
                if(n2 == 0) {
                    System.out.println("Cannot divide by zero. Please enter a valid divisor.");
                    continue;
                }
                int res = n1 / n2;
                System.out.println("Result: " + res);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
        sc.close();
    }
}