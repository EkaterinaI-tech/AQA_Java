package org.example;

import java.util.ArrayList;
import java.util.List;

public class BudgetCalculator {
    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();

        prices.add(123.10);
        prices.add(100.11);
        prices.add(520.12);
        prices.add(200.11);
        prices.add(167.10);

        double budget = 2000;
        double sum = 0;

        for (double price : prices) {
            sum += price;
        }
        System.out.println("Общая сумма товаров: " + sum);

        if (budget >= sum) {
            System.out.println("Бюджета хватает! Остаток: " + (budget - sum));
        } else {
            System.out.println("Бюджета не хватает! Нужно еще: " + (sum - budget));
        }
    }
}
