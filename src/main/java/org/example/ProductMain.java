package org.example;

public class ProductMain {
    public static void main(String[] args) {
        Product apple = new Product("Яблоко", 100.0);
        Product bread  = new Product("Хлеб", 70.0);
        Product milk = new Product("Молоко", 50.0);

        Cart myCart = new Cart();
        myCart.addProduct(apple, 3);
        myCart.addProduct(bread, 1);
        myCart.addProduct(milk, 2);
        myCart.addProduct(apple, 2);


        System.out.println("Всего товаров: " + myCart.getProductCount());
        System.out.println("Общая сумма: " + myCart.getTotalPrice());
    }
}
