package com.practice.rosreestr.serviceimpl;

import com.practice.rosreestr.dto.AccessibilityDto;
import com.practice.rosreestr.dto.HttpExchangeDto;
import com.practice.rosreestr.entity.Accessibility;
import com.practice.rosreestr.model.AccessibilityStatus;
import com.practice.rosreestr.exception.WebException;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import com.practice.rosreestr.repository.AccessibilityRepository;
import com.practice.rosreestr.service.AccessibilityService;
import com.practice.rosreestr.util.HttpExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

@RequiredArgsConstructor
@EnableScheduling
@Service
public class AccessibilityServiceImp implements AccessibilityService {

    @Value("${rosreestr-service.URLs}")
    private final String[] URLs;

    @Value("${rosreestr-service.accessibility.mock-URN}")
    private String mockURN;

    @Value("${rosreestr-service.accessibility.slow-threshold}")
    private long slowAccessibilityThreshold;

    @Value("${rosreestr-service.timeout-threshold}")
    private long timeoutThreshold;

    private final AccessibilityRepository accessibilityRepository;

    private final HttpExchangeClient httpExchangeClient;

    @Scheduled(initialDelayString = "${rosreestr-service.scheduled.initial-delay}",
            fixedRateString = "${rosreestr-service.scheduled.fixed-rate}")
    protected void scheduleAccessibility() {
        try {
            getAccessibility();
        } catch (WebException ignored) {

        }
    }

    public AccessibilityDto getAccessibility() {
        int currentURLIndex = 0;
        Accessibility accessibility = getAccessibilityForURL(URLs[currentURLIndex]);
        while (accessibility.getStatus().equals(AccessibilityStatus.FAILURE) && ++currentURLIndex < URLs.length) {
            accessibility = getAccessibilityForURL(URLs[currentURLIndex]);
        }
        if (currentURLIndex == URLs.length) {
            throw new WebServiceNotAccessibleException(
                    "Rosreestr web-services are inaccessible during checking accessibility.");
        }
        return new AccessibilityDto(
                accessibility.getTimestamp(),
                accessibility.getURL(),
                accessibility.getStatus()
        );
    }

    private Accessibility getAccessibilityForURL(String currentURL) {
        HttpRequest request = getHttpRequest(currentURL);
        HttpExchangeDto exchangeDto = httpExchangeClient.perform(request);
        AccessibilityStatus status =
                exchangeDto.getSuccessful() ?
                        convertMillisToStatus(exchangeDto.getDuration()) :
                        AccessibilityStatus.FAILURE;
        Accessibility accessibility = Accessibility
                .builder()
                .timestamp(exchangeDto.getTimestamp())
                .URL(currentURL)
                .status(status)
                .build();
        accessibilityRepository.save(accessibility);
        return accessibility;
    }

    private HttpRequest getHttpRequest(String URL) {
        return HttpRequest
                .newBuilder(URI.create(URL + "/" + mockURN))
                .timeout(Duration.ofMillis(timeoutThreshold))
                .build();
    }

    private AccessibilityStatus convertMillisToStatus(long milliseconds) {
        if (milliseconds > timeoutThreshold) {
            return AccessibilityStatus.FAILURE;
        }
        if (milliseconds > slowAccessibilityThreshold) {
            return AccessibilityStatus.SLOW;
        }
        return AccessibilityStatus.FAST;
    }

}
