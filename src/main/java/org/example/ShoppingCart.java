package org.example;

public class ShoppingCart {

    public static void main(String[] args) {
        String[] items = {"Кроссовки", "Футболка", "Рюкзак", "Джинсы", "Носки"};
        double[] prices = {1990.0, 450.0, 3200.0, 890.0, 150.0};
        double budget = 7000.0;

        // Задача 1: Вывести список товаров с ценами
        System.out.println("Корзина:");
        for (int i = 0; i < items.length; i++) {
            System.out.println("Продукт: " + items[i] + ", цена - " + prices[i]);
        }

        // Задача 2: Вывести сумму корзины
        double total = 0;
        for (double price : prices) {
            System.out.println("Всего " + total);
            System.out.println("Прибавляем " + price);
            total += price;
        }

        System.out.println("Общая сумма корзины: " + total);

        // Задача 3: Проверить, хватает ли у клиента денег
        System.out.println("Бюджет: " + budget);

        if (budget > total) {
            System.out.println("Бюджета хватает! Остаток: " + (budget - total));
        } else {
            System.out.println("Бюджета не хватает! Недостаток: " + (total - budget));
        }
    }
    }
