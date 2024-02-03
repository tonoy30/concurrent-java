package me.tonoy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fibonacci implements Runnable {
    private final long num;
    private final String taskId;

    public Fibonacci(long num, String taskId) {
        this.num = num;
        this.taskId = taskId;
    }

    private BigDecimal fib(long num) {
        if (num == 0) {
            return BigDecimal.ZERO;
        } else if (num == 1) {
            return BigDecimal.ONE;
        } else {
            return fib(num - 1).add(fib(num - 2));
        }
    }

    @Override
    public void run() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        System.out.println("String task " + taskId + " at " + dateTimeFormatter.format(LocalDateTime.now()));
        BigDecimal fib = fib(num);
        System.out.println("Ending task " + taskId + " at " + dateTimeFormatter.format(LocalDateTime.now()) + " Fib " + fib);
    }
}
