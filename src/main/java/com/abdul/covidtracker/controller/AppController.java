package com.abdul.covidtracker.controller;

import com.abdul.covidtracker.model.GlobalStats;
import com.abdul.covidtracker.model.HistoricalData;
import com.abdul.covidtracker.model.CountryStats;
import com.abdul.covidtracker.service.CovidService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private final CovidService covidService;

    public AppController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RestController
    @RequestMapping("/api")
    static class CovidApiController {
        private final CovidService covidService;

        CovidApiController(CovidService covidService) {
            this.covidService = covidService;
        }

        @GetMapping("/summary")
        public GlobalStats getSummary() {
            return covidService.getGlobalSummary();
        }

        @GetMapping("/countries")
        public List<CountryStats> getCountries() {
            return covidService.getCountryStats();
        }

        @GetMapping("/history/{country}")
        public HistoricalData getHistory(@PathVariable String country) {
            return covidService.getHistoricalData(country);
        }
    }
}
