package org.example;

import java.util.ArrayList;
import java.util.List;

public class FruitsList {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        fruits.add("Яблоко");
        fruits.add("Банан");
        fruits.add("Апельсин");
        fruits.add("Груша");
        fruits.add("Киви");

        for (int i = 0; i < fruits.size(); i++) {
            System.out.println((i + 1) + ". " + fruits.get(i));
        }
    }
}