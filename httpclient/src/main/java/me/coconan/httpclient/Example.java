package me.coconan.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Example {
    public static void main(String[] args) {
        try (CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault()) {
            httpAsyncClient.start();
            HttpGet request = new HttpGet("http://httpbin.org/get");
            Future<HttpResponse> future = httpAsyncClient.execute(request, null);
            HttpResponse response = future.get();
            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Shutting down");
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
