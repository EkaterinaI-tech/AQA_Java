package org.example;

import java.util.ArrayList;
import java.util.List;

public class Shopping {
    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();

        prices.add(123.10);
        prices.add(100.11);
        prices.add(520.12);
        prices.add(300.11);
        prices.add(167.10);

        double budget = 1000;
        double spentMoney = 0;
        int itemsCount = 0;

        int i = 0;
        while (i < prices.size() && spentMoney + prices.get(i) <= budget) {
            spentMoney += prices.get(i);
            itemsCount++;
            i++;
        }

        int leftItems = prices.size() - itemsCount;

        System.out.println("Куплено: " + itemsCount + " товара на сумму " + spentMoney);
        System.out.println("Остаток бюджета: " + (budget - spentMoney));
        System.out.println("Не куплено: " + leftItems + " товара");
    }
}