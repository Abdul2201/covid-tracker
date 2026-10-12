package com.abdul.covidtracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "covid_data")
public class CovidData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "country_code")
    private String countryCode;
    
    @Column(name = "total_cases")
    private Long totalCases;
    
    @Column(name = "total_deaths")
    private Long totalDeaths;
    
    @Column(name = "total_recovered")
    private Long totalRecovered;
    
    @Column(name = "active_cases")
    private Long activeCases;
    
    @Column(name = "critical_cases")
    private Long criticalCases;
    
    @Column(name = "cases_today")
    private Long casesToday;
    
    @Column(name = "deaths_today")
    private Long deathsToday;
    
    @Column(name = "population")
    private Long population;
    
    @Column(name = "recovery_rate")
    private Double recoveryRate;
    
    @Column(name = "mortality_rate")
    private Double mortalityRate;
    
    @Column(name = "tests_performed")
    private Long testsPerformed;
    
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    
    @Column(name = "continent")
    private String continent;

    // Constructors
    public CovidData() {}

    public CovidData(String country, String countryCode, Long totalCases, Long totalDeaths, 
                     Long totalRecovered, Long activeCases, Long criticalCases,
                     Long casesToday, Long deathsToday, Long population, 
                     Double recoveryRate, Double mortalityRate, Long testsPerformed,
                     String continent) {
        this.country = country;
        this.countryCode = countryCode;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.activeCases = activeCases;
        this.criticalCases = criticalCases;
        this.casesToday = casesToday;
        this.deathsToday = deathsToday;
        this.population = population;
        this.recoveryRate = recoveryRate;
        this.mortalityRate = mortalityRate;
        this.testsPerformed = testsPerformed;
        this.lastUpdated = LocalDateTime.now();
        this.continent = continent;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public Long getTotalCases() { return totalCases; }
    public void setTotalCases(Long totalCases) { this.totalCases = totalCases; }

    public Long getTotalDeaths() { return totalDeaths; }
    public void setTotalDeaths(Long totalDeaths) { this.totalDeaths = totalDeaths; }

    public Long getTotalRecovered() { return totalRecovered; }
    public void setTotalRecovered(Long totalRecovered) { this.totalRecovered = totalRecovered; }

    public Long getActiveCases() { return activeCases; }
    public void setActiveCases(Long activeCases) { this.activeCases = activeCases; }

    public Long getCriticalCases() { return criticalCases; }
    public void setCriticalCases(Long criticalCases) { this.criticalCases = criticalCases; }

    public Long getCasesToday() { return casesToday; }
    public void setCasesToday(Long casesToday) { this.casesToday = casesToday; }

    public Long getDeathsToday() { return deathsToday; }
    public void setDeathsToday(Long deathsToday) { this.deathsToday = deathsToday; }

    public Long getPopulation() { return population; }
    public void setPopulation(Long population) { this.population = population; }

    public Double getRecoveryRate() { return recoveryRate; }
    public void setRecoveryRate(Double recoveryRate) { this.recoveryRate = recoveryRate; }

    public Double getMortalityRate() { return mortalityRate; }
    public void setMortalityRate(Double mortalityRate) { this.mortalityRate = mortalityRate; }

    public Long getTestsPerformed() { return testsPerformed; }
    public void setTestsPerformed(Long testsPerformed) { this.testsPerformed = testsPerformed; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
}
