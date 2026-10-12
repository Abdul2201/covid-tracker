package com.abdul.covidtracker.service;

import com.abdul.covidtracker.entity.CovidData;
import com.abdul.covidtracker.dto.CovidStatsDto;
import com.abdul.covidtracker.repository.CovidDataRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CovidDataService {

    private final CovidDataRepository covidDataRepository;

    public CovidDataService(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }

    public void saveCovidData(CovidData covidData) {
        covidDataRepository.save(covidData);
    }

    public Optional<CovidData> getCovidDataByCountry(String country) {
        return covidDataRepository.findByCountry(country);
    }

    public Optional<CovidData> getCovidDataByCountryCode(String countryCode) {
        return covidDataRepository.findByCountryCode(countryCode);
    }

    public List<CovidData> getCovidDataByContinent(String continent) {
        return covidDataRepository.findByContinent(continent);
    }

    public List<CovidData> getTop10Countries() {
        return covidDataRepository.findTop10ByTotalCases();
    }

    public List<CovidData> getCountriesWithHighActiveCases(Long threshold) {
        return covidDataRepository.findCountriesWithHighActiveCases(threshold);
    }

    public List<CovidData> getTop10ByMortalityRate() {
        return covidDataRepository.findTop10ByMortalityRate();
    }

    public long getTotalCountries() {
        return covidDataRepository.countTotalCountries();
    }

    public Long getTotalGlobalCases() {
        return covidDataRepository.getTotalGlobalCases();
    }

    public Long getTotalGlobalDeaths() {
        return covidDataRepository.getTotalGlobalDeaths();
    }

    public Long getTotalGlobalRecovered() {
        return covidDataRepository.getTotalGlobalRecovered();
    }

    public List<CovidStatsDto> getAllCovidStats() {
        return covidDataRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteCovidData(Long id) {
        covidDataRepository.deleteById(id);
    }

    public List<CovidData> getAllCovidData() {
        return covidDataRepository.findAll();
    }

    private CovidStatsDto convertToDto(CovidData covidData) {
        return new CovidStatsDto(
                covidData.getCountry(),
                covidData.getTotalCases(),
                covidData.getTotalDeaths(),
                covidData.getTotalRecovered(),
                covidData.getActiveCases(),
                covidData.getRecoveryRate(),
                covidData.getMortalityRate()
        );
    }
}
