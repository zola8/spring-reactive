package com.mz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.IntStream;

public class HttpClientSynchronous {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientSynchronous.class);

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    public static void main(String[] args) {
        LOGGER.info("START");

        IntStream.rangeClosed(1, 3).forEach(num -> {
            String str = "t_" + String.format("%03d", num);
            HttpResponse<String> response = sendHttpRequest(str);
            String resp = response == null ? "<null>" : response.body();
            LOGGER.info("  Response: {}", resp);
        });

        LOGGER.info("END");
    }

    private static HttpResponse<String> sendHttpRequest(String str) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/echo?str=" + str))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
