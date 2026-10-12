package com.abdul.covidtracker.controller;

import com.abdul.covidtracker.entity.CovidData;
import com.abdul.covidtracker.dto.CovidStatsDto;
import com.abdul.covidtracker.service.CovidDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/covid")
@CrossOrigin(origins = "*")
public class CovidDataController {

    private final CovidDataService covidDataService;

    public CovidDataController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CovidData>> getAllCovidData() {
        return ResponseEntity.ok(covidDataService.getAllCovidData());
    }

    @GetMapping("/stats")
    public ResponseEntity<List<CovidStatsDto>> getAllStats() {
        return ResponseEntity.ok(covidDataService.getAllCovidStats());
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<Optional<CovidData>> getByCountry(@PathVariable String country) {
        return ResponseEntity.ok(covidDataService.getCovidDataByCountry(country));
    }

    @GetMapping("/code/{countryCode}")
    public ResponseEntity<Optional<CovidData>> getByCountryCode(@PathVariable String countryCode) {
        return ResponseEntity.ok(covidDataService.getCovidDataByCountryCode(countryCode));
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CovidData>> getByContinent(@PathVariable String continent) {
        return ResponseEntity.ok(covidDataService.getCovidDataByContinent(continent));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<CovidData>> getTop10() {
        return ResponseEntity.ok(covidDataService.getTop10Countries());
    }

    @GetMapping("/high-active/{threshold}")
    public ResponseEntity<List<CovidData>> getCountriesWithHighActiveCases(
            @PathVariable Long threshold) {
        return ResponseEntity.ok(covidDataService.getCountriesWithHighActiveCases(threshold));
    }

    @GetMapping("/top-mortality")
    public ResponseEntity<List<CovidData>> getTop10ByMortalityRate() {
        return ResponseEntity.ok(covidDataService.getTop10ByMortalityRate());
    }

    @GetMapping("/global-summary")
    public ResponseEntity<CovidGlobalSummary> getGlobalSummary() {
        return ResponseEntity.ok(new CovidGlobalSummary(
                covidDataService.getTotalGlobalCases(),
                covidDataService.getTotalGlobalDeaths(),
                covidDataService.getTotalGlobalRecovered(),
                covidDataService.getTotalCountries()
        ));
    }

    @PostMapping("/add")
    public ResponseEntity<CovidData> addCovidData(@RequestBody CovidData covidData) {
        covidDataService.saveCovidData(covidData);
        return ResponseEntity.ok(covidData);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CovidData> updateCovidData(@PathVariable Long id, @RequestBody CovidData covidData) {
        covidData.setId(id);
        covidDataService.saveCovidData(covidData);
        return ResponseEntity.ok(covidData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCovidData(@PathVariable Long id) {
        covidDataService.deleteCovidData(id);
        return ResponseEntity.noContent().build();
    }

    public static class CovidGlobalSummary {
        private Long totalCases;
        private Long totalDeaths;
        private Long totalRecovered;
        private Long totalCountries;

        public CovidGlobalSummary(Long totalCases, Long totalDeaths, Long totalRecovered, Long totalCountries) {
            this.totalCases = totalCases;
            this.totalDeaths = totalDeaths;
            this.totalRecovered = totalRecovered;
            this.totalCountries = totalCountries;
        }

        public Long getTotalCases() { return totalCases; }
        public Long getTotalDeaths() { return totalDeaths; }
        public Long getTotalRecovered() { return totalRecovered; }
        public Long getTotalCountries() { return totalCountries; }
    }
}
