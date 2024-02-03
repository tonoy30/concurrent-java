package me.tonoy.threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Watch {
    private static void printCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:s a");
        String currentTime = LocalDateTime.now().format(formatter);
        System.out.println(currentTime);
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(Watch::printCurrentTime, 0, 1, TimeUnit.SECONDS);
    }
}
