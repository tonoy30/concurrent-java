package me.tonoy.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private static final String DOCUMENT = "<html>" +
            "<head>" +
            "<title>Single Threaded Server </title>" +
            "</head>" +
            "<body>" +
            "<h1>It works </h1>" +
            "</body>" +
            "</html>";
    private static final String HEADER = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset=UTF-8\r\n" +
            "Content-Length: " + DOCUMENT.length()
            + "\r\n\r\n";

    private static void serveRequest(Socket conn) {
        System.out.println("New connection request from: " + conn.toString());
        try (OutputStream os = conn.getOutputStream(); PrintWriter out = new PrintWriter(os)) {
            out.println(HEADER);
            out.println(DOCUMENT);
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Executor executor = Executors.newFixedThreadPool(10);
        while (true) {
            Socket conn = serverSocket.accept();
            executor.execute(() -> serveRequest(conn));
        }
    }

}
