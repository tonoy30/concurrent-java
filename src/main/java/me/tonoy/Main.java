package me.tonoy;

public class Main {
    public static void demo() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        System.out.println("Current Thread Name: " + currentThread.getName());

        ExtendedThread et = new ExtendedThread();
        et.setName("EXTENDED_THREAD");
        et.start();

        Thread th = new Thread(new RunnableThread());
        th.setName("RUNNABLE_THREAD");
        th.start();
        Watch watch = new Watch();
        Thread watchTh = new Thread(watch);
        watchTh.setName("WATCH");
        watchTh.start();
        Thread.sleep(500);
        watch.shutdown();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Fibonacci(45, "FIB TASK# 1"));
        Thread t2 = new Thread(new Fibonacci(45, "FIB TASK# 2"));
        Thread t3 = new Thread(new Fibonacci(45, "FIB TASK# 3"));
        Thread t4 = new Thread(new Fibonacci(45, "FIB TASK# 4"));
        Thread t5 = new Thread(new Fibonacci(45, "FIB TASK# 5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

