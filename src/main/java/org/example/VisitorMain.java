package org.example;

public class VisitorMain {
    public static void main(String[] args) {

        Visitor v1 = new Visitor("Olga");
        System.out.println(Visitor.getTotalVisitors());

        Visitor v2 = new Visitor("Polina");
        System.out.println(Visitor.getTotalVisitors());

        Visitor v3 = new Visitor("Nikita");
        System.out.println(Visitor.getTotalVisitors());

        Visitor v4 = new Visitor("Oleg");
        System.out.println(Visitor.getTotalVisitors());

        Visitor v5 = new Visitor("Ivan");
        System.out.println(Visitor.getTotalVisitors());

        System.out.println("v1 говорит: " + v1.getTotalVisitors());
        System.out.println("v5 говорит: " + v5.getTotalVisitors());
    }
}
/*
 Почему значение одинаковое, если вызвать getTotalVisitors() через любой из пяти объектов?

 Потому что переменная totalVisitors статичная и значит она одна на весь класс,
 а не у каждого объекта своя индивидуальная.
 */