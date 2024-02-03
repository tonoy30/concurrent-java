package me.tonoy.liveness;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void running() {
        synchronized (lock1) {
            log("Acquired Lock1");
            synchronized (lock2) {
                log("Acquired Lock2");
                System.out.println("Running");
                log("About to release Lock2");
            }
            log("About to release Lock1");
        }
    }

    public void walking() {
        synchronized (lock2) {
            log("Acquired Lock2");
            synchronized (lock1) {
                log("Acquired Lock1");
                System.out.println("Running");
                log("About to release Lock1");
            }
            log("About to release Lock2");
        }
    }

    private static void log(String msg) {
        LocalDateTime now = LocalDateTime.now();
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(now);
        String threadName = Thread.currentThread().getName();

        System.out.printf("%12s %s: %s%n", time, threadName, msg);
    }
}
