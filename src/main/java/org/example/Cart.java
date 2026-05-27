package org.example;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int count = products.get(product);
            products.put(product, count + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Map.Entry<Product, Integer> stroka : products.entrySet()) {

            Product item = stroka.getKey();
            int count = stroka.getValue();
            sum = sum + (item.getPrice() * count);
        }
        return sum;
    }

    public int getProductCount() {
        int count = 0;
        for (Map.Entry<Product, Integer> stroka : products.entrySet()) {
            count = count + stroka.getValue();
        }
        return count;
    }
}