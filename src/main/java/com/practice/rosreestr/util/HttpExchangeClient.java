package com.practice.rosreestr.util;

import com.practice.rosreestr.dto.HttpExchangeDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@Component
public class HttpExchangeClient {

    public HttpExchangeDto perform(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        Date timestamp = new Date(System.currentTimeMillis());
        HttpResponse<String> response;
        long start = System.nanoTime();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            long end = System.nanoTime();
            long duration = (end - start) / 1000000;
            return getFailureExchange(timestamp, duration);
        }
        long end = System.nanoTime();
        long duration = (end - start) / 1000000;
        return HttpExchangeDto
                .builder()
                .successful(true)
                .timestamp(timestamp)
                .duration(duration)
                .body(response.body())
                .build();
    }

    private HttpExchangeDto getFailureExchange(Date timestamp, long duration) {
        return HttpExchangeDto
                .builder()
                .successful(false)
                .timestamp(timestamp)
                .duration(duration)
                .body("")
                .build();
    }

}
