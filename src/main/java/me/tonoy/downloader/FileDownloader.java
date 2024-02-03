package me.tonoy.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

public class FileDownloader extends Thread {
    private final String url;
    private final String fileName;

    public FileDownloader(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Started to download: " + fileName);
            URL resourceToDownload = new URL(url);
            URLConnection connection = resourceToDownload.openConnection();
            InputStream input = connection.getInputStream();
            File file = new File(fileName);
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            input.close();

        } catch (IOException e) {
            System.out.println("Failed to download the file: " + e.getMessage());
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }
}
