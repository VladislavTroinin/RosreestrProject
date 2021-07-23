package com.practice.rosreestr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.practice.rosreestr.dto.HttpExchangeDto;
import com.practice.rosreestr.dto.InformationDto;
import com.practice.rosreestr.entity.Information;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import com.practice.rosreestr.repository.InformationRepository;
import com.practice.rosreestr.util.HttpExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class InformationServiceImp implements InformationService {

    @Value("${rosreestr-service.URLs}")
    private final String[] URLs;

    @Value("${rosreestr-service.timeout-threshold}")
    private long timeoutThreshold;

    @Value("${rosreestr-service.info.address-json-path}")
    private String addressJSONPath;

    @Value("${rosreestr-service.info.cost-json-path}")
    private String cadastreCostJSONPath;

    private final InformationRepository informationRepository;

    private final HttpExchangeClient httpExchangeClient;

    public InformationDto getInformation(Integer typeId, String objectId) throws WebServiceNotAccessibleException {
        int currentURLIndex = 0;
        Information information = getInformationForURL(URLs[currentURLIndex], typeId, objectId);
        while (Objects.isNull(information) && ++currentURLIndex < URLs.length) {
            information = getInformationForURL(URLs[currentURLIndex], typeId, objectId);
        }
        if (currentURLIndex == URLs.length) {
            throw new WebServiceNotAccessibleException(
                    "Rosreestr web-services are inaccessible during receiving information.");
        }
        informationRepository.save(information);
        return new InformationDto(
                information.getTypeNumber(),
                information.getCadastreNumber(),
                information.getAddress(),
                information.getCadastreCost()
        );
    }

    private Information getInformationForURL(String currentURL, Integer typeId, String objectId) {
        HttpRequest request = getHttpRequest(currentURL, typeId, objectId);
        HttpExchangeDto exchangeDto = httpExchangeClient.perform(request);
        if (!exchangeDto.getSuccessful()) {
            return null;
        }
        JsonNode JSONTree = getResponseJSONTree(exchangeDto.getBody());
        if (Objects.isNull(JSONTree)) {
            return null;
        }
        JsonNode addressNode = JSONTree.at(addressJSONPath);
        JsonNode costNode = JSONTree.at(cadastreCostJSONPath);
        if (addressNode.isMissingNode() || costNode.isMissingNode()) {
            return null;
        }
        BigDecimal cost = BigDecimal.valueOf(costNode.asDouble());
        return Information
                .builder()
                .requestTimestamp(exchangeDto.getTimestamp())
                .URL(currentURL)
                .typeNumber(typeId)
                .cadastreNumber(objectId)
                .address(addressNode.asText())
                .cadastreCost(cost)
                .build();
    }

    private HttpRequest getHttpRequest(String URL, Integer typeId, String objectId) {
        String currentURI = URL + "/" + typeId + "/" + objectId;
        return HttpRequest
                .newBuilder(URI.create(currentURI))
                .timeout(Duration.ofMillis(timeoutThreshold))
                .build();
    }

    private JsonNode getResponseJSONTree(String responseBody) {
        try {
            return new JsonMapper().readTree(responseBody);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
