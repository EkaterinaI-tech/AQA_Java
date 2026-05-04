package org.example;

import java.util.Scanner;public class RealEstatePayback {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите стоимость недвижимости: ");
        float propertyPrice = sc.nextFloat();

        System.out.println("Введите аренду за месяц: ");
        float monthlyRent = sc.nextFloat();

        System.out.println("Введите годовую индексацию (%): ");
        float yearlyIndexation = sc.nextFloat();

        float totalEarned = 0;
        int months = 0;

        while (totalEarned < propertyPrice) {
            months++;
            totalEarned += monthlyRent;

            if (months % 12 == 0) {
                monthlyRent += monthlyRent * (yearlyIndexation / 100);
            }
        }

        System.out.println("Заработали: " + totalEarned + " рублей за " + months + "месяцев");
    }
}