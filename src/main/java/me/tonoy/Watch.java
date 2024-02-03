package me.tonoy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Watch implements Runnable {
    private volatile boolean running = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:s a");

    @Override
    public void run() {
        while (running) {
            System.out.println(LocalDateTime.now().format(formatter));
            sleepOneSecond();
            if (Thread.interrupted()) {
                System.out.println("Thread is Interrupted");
                return;
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }

    private static void sleepOneSecond() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
