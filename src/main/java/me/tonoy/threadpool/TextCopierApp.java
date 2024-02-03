package me.tonoy.threadpool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TextCopierApp {
    public static void main(String[] args) {
        List<TextCopier> tasks = Arrays.asList(
                new TextCopier("https://dip-mazumder.medium.com/microservice-distributed-transactions-101-guide-to-choose-the-best-strategy-5a9841711bae"),
                new TextCopier("https://towardsdatascience.com/what-is-the-biggest-number-cf0566eba6b7"),
                new TextCopier("https://cli.github.com/manual/gh_auth_status")
        );
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            List<Future<String>> futures = executorService.invokeAll(tasks);
            for (Future<String> future : futures) {
                String result = future.get();
                System.out.println(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
        executorService.shutdown();
    }
}
