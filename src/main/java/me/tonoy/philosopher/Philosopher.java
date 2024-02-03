package me.tonoy.philosopher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            keepThinkingAndEating();
        }
    }

    public void keepThinkingAndEating() {
        think();
        synchronized (leftFork) {
            log("grabbed left fork");
            synchronized (rightFork) {
                log("grabbed right fork");
                eat();
                log("put down right fork");
            }
            log("put down left fork");
        }
    }

    private void think() {
        log("thinking");
    }

    private void eat() {
        try {
            log("Eating");
            int eatingTime = getRandomNumber();
            TimeUnit.NANOSECONDS.sleep(eatingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    private void log(String msg) {
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        System.out.printf("%12s Thread: %s %s: %s%n", time, threadName, name, msg);
        System.out.flush();
    }

    private static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 50;
    }
}

