package me.tonoy.downloader;

public class Downloader {
    public static void main(String[] args) throws InterruptedException {
        FileDownloader dl1 = new FileDownloader(
                "https://cdn.pixabay.com/photo/2016/10/21/14/50/plouzane-1758197_1280.jpg",
                "plouzane.jpg");
        FileDownloader dl2 = new FileDownloader(
                "https://cdn.pixabay.com/photo/2023/09/23/12/35/street-8270869_1280.jpg",
                "street.jpg");

        HeartBeat heartBeat = new HeartBeat();
        dl1.start();
        dl2.start();
        heartBeat.start();

        // wait for child thead to finish
        try {
            dl1.join();
            dl2.join();

            heartBeat.shutdown();
            heartBeat.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nThe download is completed");
    }
}
