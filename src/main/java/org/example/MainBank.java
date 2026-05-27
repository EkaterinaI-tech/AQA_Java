package org.example;

public class MainBank {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.setOwnerName("Olga");
        account.setBalance(1000.0);
        System.out.println("Баланс Ольги: " + account.getBalance());

        account.setBalance(-500.0);
        System.out.println("Итоговый баланс: " + account.getBalance());
    }
}