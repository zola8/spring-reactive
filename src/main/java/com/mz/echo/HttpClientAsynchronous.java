package com.mz.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class HttpClientAsynchronous {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientAsynchronous.class);

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final int MAX = 500;


    public static void main(String[] args) {
        LOGGER.info("START");
        List<CompletableFuture> futures = new ArrayList<>(MAX);

        IntStream.rangeClosed(1, MAX).forEach(num -> {
            String str = "t_" + String.format("%03d", num);
            LOGGER.info("  Sending request: {}", str);
            CompletableFuture<HttpResponse<String>> response = sendHttpRequest(str);
            futures.add(response);
        });

        LOGGER.info("Collecting responses...");

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[MAX]));

        try {
            allOf.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        futures.forEach(item -> {
            try {
                HttpResponse<String> it = (HttpResponse<String>) item.get();
                LOGGER.info("  Response: {}", it.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        LOGGER.info("END");
    }

    private static CompletableFuture<HttpResponse<String>> sendHttpRequest(String str) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/echo?str=" + str))
                .build();
        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
