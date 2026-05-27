package org.example;

public class MathHelper {

    public static int sum(int a, int b) {
        return a+b;
    }

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static boolean isEven(int number) {
        return (number % 2 == 0);
    }
}
