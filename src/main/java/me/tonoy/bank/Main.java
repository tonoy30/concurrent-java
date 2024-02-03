package me.tonoy.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(100);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        Thread withdraw = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started at " + formatter.format(LocalDateTime.now()));
            account.withdraw(100);
        });
        withdraw.setName("WITHDRAW THREAD");

        Thread deposit = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started at " + formatter.format(LocalDateTime.now()));

            account.deposit(100);
        });
        deposit.setName("DEPOSIT THREAD");

        withdraw.start();
        deposit.start();

        try {
            withdraw.join();
            deposit.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
        System.out.println("Current balance available in account:  " + account.getBalance());
    }
}
