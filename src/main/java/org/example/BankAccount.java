package org.example;

public class BankAccount {
    String ownerName;
    Double balance;

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setBalance(Double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Баланс не может быть отрицательным");
        }
    }

    public Double getBalance() {
        return balance;
    }
}