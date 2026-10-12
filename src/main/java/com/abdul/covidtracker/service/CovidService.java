package com.abdul.covidtracker.service;

import com.abdul.covidtracker.model.CountryStats;
import com.abdul.covidtracker.model.GlobalStats;
import com.abdul.covidtracker.model.HistoricalData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CovidService {

    private final WebClient webClient;

    public CovidService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://disease.sh")
                .build();
    }

    public GlobalStats getGlobalSummary() {
        return webClient.get()
                .uri("/v3/covid-19/all")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new RuntimeException("Client error fetching global summary")))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Server error fetching global summary")))
                .bodyToMono(GlobalStats.class)
                .block();
    }

    public List<CountryStats> getCountryStats() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v3/covid-19/countries")
                        .queryParam("sort", "cases")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CountryStats>>() {})
                .block();
    }

    public HistoricalData getHistoricalData(String country) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v3/covid-19/historical/{country}")
                        .queryParam("lastdays", "all")
                        .build(country))
                .retrieve()
                .bodyToMono(HistoricalData.class)
                .block();
    }
}
