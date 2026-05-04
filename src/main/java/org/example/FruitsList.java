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
            int number = i + 1;
            System.out.println(number + ". " + fruits.get(i));
        }
    }
}

//почему for-each не лучший выбор
//Потому что в for-each нет нумерации, не дает индекс,
//поэтому нельзя было бы просто написать int number = i + 1;
// Нужно было бы делать отдельную переменную, чтобы отдельно считать нумерацию