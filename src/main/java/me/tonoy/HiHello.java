package me.tonoy;

public class HiHello {
    static boolean running = false;

    public static void main(String[] args) {
        Thread th1 = new Thread(() -> {
            while (!running) {

            }
            System.out.println("Hello");
        });
        th1.start();

        Thread th2 = new Thread(() -> {
            running = true;
            System.out.println("Hi");
        });
        th2.start();
    }
}
