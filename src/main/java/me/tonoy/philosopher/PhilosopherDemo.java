package me.tonoy.philosopher;

public class PhilosopherDemo {
    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher("Philosopher " + (i + 1), rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher("Philosopher " + (i + 1), leftFork, rightFork);
            }

            philosophers[i].start();
        }
    }
}
