/*Classes & Objects in Java
Write a program that takes input from the user and creates an object of a class named 'Person'. 
The 'Person' class should have two member variables: 'name' and 'age'. The program should prompt 
the user to enter their name and age, create a 'Person' object with the entered values, and then 
display the name and age of the person. */

import java.util.Scanner;

public class Person {
    String name;
    int age;
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int age = sc.nextInt();

        Person p = new Person();
        p.name = name;
        p.age = age;

        System.out.println("Name: " + p.name);
        System.out.println("Age: " + p.age);

        sc.close();
    }
}
