package org.example;

public class Person {
    String firstName;
    String lastName;
    int age;

    public void introduce() {
        System.out.println("Привет, меня зовут " + firstName + " " + lastName + ". Мне " + age + " лет.");
    }
}
