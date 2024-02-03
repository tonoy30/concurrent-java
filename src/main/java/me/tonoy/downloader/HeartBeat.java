package me.tonoy.downloader;

import java.util.concurrent.TimeUnit;

public class HeartBeat extends Thread {
    private volatile boolean beating = true;

    @Override
    public void run() {
        String[] dots = {".", "..", "...", "...."};
        while (beating) {
            for (String dot : dots) {
                System.out.println(dot);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        beating = false;
    }
}
