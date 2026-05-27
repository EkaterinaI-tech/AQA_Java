package org.example;

public class MathMain {
    public static void main(String[] args) {
        int total = MathHelper.sum(10, 5);
        System.out.println(total);

        int theLargest = MathHelper.max(10, 5);
        System.out.println(theLargest);

        boolean evenOdd = MathHelper.isEven(10);
        System.out.println(evenOdd);
    }
}
