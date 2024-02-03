package me.tonoy.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Buffer {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(100);

    public void addItem(Integer val) {
        try {
            queue.put(val);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public Integer getItem() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                int anInt = random.nextInt();
                System.out.println("Producer produce: " + anInt);
                buffer.addItem(anInt);
                delay(1000);
            }
        });
        producer.setName("Buffer Producer");

        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Integer anInt = buffer.getItem();
                System.out.println("Consumer consumed: " + anInt);
                delay(1500);
            }
        });
        consumer.setName("Buffer Consumer");

        producer.start();
        consumer.start();
    }

    private static void delay(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }
}
