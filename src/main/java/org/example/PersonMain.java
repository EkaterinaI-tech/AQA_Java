package org.example;

public class PersonMain {
    public static void main(String[] args) {
        Person person = new Person();

        person.firstName = "Nikolay";
        person.lastName = "Baskov";
        person.age = 25;

        person.introduce();
    }
}
