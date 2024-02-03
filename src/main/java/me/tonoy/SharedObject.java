package me.tonoy;

import java.util.concurrent.TimeUnit;

public class SharedObject {
    private final ThreadLocal<Integer> number = new ThreadLocal<>();


    public Integer getNumber() {
        return number.get();
    }

    public void setNumber(Integer value) {
        number.set(value);
    }

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        setRandomNumber(sharedObject);

        Thread th1 = new Thread(() -> {
            setRandomNumber(sharedObject);
        });
        Thread th2 = new Thread(() -> {
            setRandomNumber(sharedObject);
        });

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }

        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Value: " + sharedObject.getNumber());
    }

    private static void setRandomNumber(SharedObject sharedObject) {
        int number = (int) (Math.random() * 100);
        sharedObject.setNumber(number);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Value: " + sharedObject.getNumber());
    }

}
