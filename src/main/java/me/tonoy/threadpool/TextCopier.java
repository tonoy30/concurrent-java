package me.tonoy.threadpool;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class TextCopier implements Callable<String> {
    private final String target;

    public TextCopier(String target) {
        this.target = target;
    }

    @Override
    public String call() throws Exception {
        URL url = new URI(target).toURL();
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream is = url.openConnection().getInputStream()) {
            Scanner scanner = new Scanner(is);
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
        }
        return stringBuilder.toString();
    }
}
