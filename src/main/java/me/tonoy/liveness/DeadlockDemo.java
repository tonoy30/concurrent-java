package me.tonoy.liveness;

import java.util.concurrent.TimeUnit;

public class DeadlockDemo {
    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();

        Thread running = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadlock.running();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
        });
        running.setName("Running Thread");
        Thread walking = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadlock.walking();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new AssertionError(e);
                }
            }
        });
        walking.setName("Walking Thread");

        running.start();
        walking.start();
    }
}
