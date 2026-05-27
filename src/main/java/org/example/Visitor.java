package org.example;

public class Visitor {
    String firstName;
    private static int totalVisitors = 0;

    public Visitor(String firstName) {
        this.firstName = firstName;
        totalVisitors++;
    }

    public static int getTotalVisitors() {
        return totalVisitors;
    }
}
