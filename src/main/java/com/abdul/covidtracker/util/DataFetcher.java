package com.abdul.covidtracker.util;

import com.abdul.covidtracker.entity.CovidData;
import com.abdul.covidtracker.service.CovidDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataFetcher {

    private final RestTemplate restTemplate;
    private final CovidDataService covidDataService;
    private final ObjectMapper objectMapper;
    private final String API_URL = "https://disease.sh/v3/covid-19/countries";

    public DataFetcher(RestTemplate restTemplate, CovidDataService covidDataService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.covidDataService = covidDataService;
        this.objectMapper = objectMapper;
    }

    public void fetchAndSaveCovidData() {
        try {
            String response = restTemplate.getForObject(API_URL, String.class);
            JsonNode countries = objectMapper.readTree(response);

            for (JsonNode country : countries) {
                CovidData covidData = parseCovidData(country);
                covidDataService.saveCovidData(covidData);
            }
        } catch (Exception e) {
            System.err.println("Error fetching COVID data: " + e.getMessage());
        }
    }

    private CovidData parseCovidData(JsonNode node) {
        CovidData covidData = new CovidData();
        
        covidData.setCountry(node.get("country").asText());
        covidData.setCountryCode(node.get("countryInfo").get("iso2").asText());
        covidData.setContinent(node.has("continent") ? node.get("continent").asText() : "Unknown");
        covidData.setTotalCases(node.get("cases").asLong());
        covidData.setTotalDeaths(node.get("deaths").asLong());
        covidData.setTotalRecovered(node.get("recovered").asLong());
        covidData.setActiveCases(node.get("active").asLong());
        covidData.setCriticalCases(node.get("critical").asLong());
        covidData.setCasesToday(node.get("todayCases").asLong());
        covidData.setDeathsToday(node.get("todayDeaths").asLong());
        covidData.setPopulation(node.get("population").asLong());
        covidData.setTestsPerformed(node.get("tests").asLong());
        
        // Calculate rates
        long totalCases = covidData.getTotalCases();
        long totalDeaths = covidData.getTotalDeaths();
        long totalRecovered = covidData.getTotalRecovered();
        
        covidData.setMortalityRate(totalCases > 0 ? (double) totalDeaths / totalCases * 100 : 0.0);
        covidData.setRecoveryRate(totalCases > 0 ? (double) totalRecovered / totalCases * 100 : 0.0);
        
        covidData.setLastUpdated(LocalDateTime.now());
        
        return covidData;
    }
}
