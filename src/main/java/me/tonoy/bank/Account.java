package me.tonoy.bank;

import java.util.concurrent.TimeUnit;

public class Account {
    private long balance;

    public Account(long initialAmount) {
        this.balance = initialAmount;
    }

    public long getBalance() {
        return balance;
    }

    public synchronized void withdraw(long amount) {
        System.out.println("Inside the withdraw method! Lock acquired!");
        sleep();
        System.out.println("Withdrawing => " + amount);
        long newBalance = this.balance - amount;
        System.out.println("New balance is => " + newBalance);
        this.balance = newBalance;
        System.out.println("End of the withdraw method! releasing the lock!");
    }

    public synchronized void deposit(long amount) {
        System.out.println("Inside the deposit method! Lock acquired!");
        sleep();
        System.out.println("Depositing => " + amount);
        long newBalance = this.balance + amount;
        System.out.println("New balance is => " + newBalance);
        this.balance = newBalance;
        System.out.println("End of the deposit method! releasing the lock!");
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }
}
