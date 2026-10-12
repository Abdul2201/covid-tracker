package com.abdul.covidtracker.dto;

public class CovidStatsDto {
    private String country;
    private Long totalCases;
    private Long totalDeaths;
    private Long totalRecovered;
    private Long activeCases;
    private Double recoveryRate;
    private Double mortalityRate;

    public CovidStatsDto() {}

    public CovidStatsDto(String country, Long totalCases, Long totalDeaths, 
                         Long totalRecovered, Long activeCases, 
                         Double recoveryRate, Double mortalityRate) {
        this.country = country;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.activeCases = activeCases;
        this.recoveryRate = recoveryRate;
        this.mortalityRate = mortalityRate;
    }

    // Getters and Setters
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Long getTotalCases() { return totalCases; }
    public void setTotalCases(Long totalCases) { this.totalCases = totalCases; }

    public Long getTotalDeaths() { return totalDeaths; }
    public void setTotalDeaths(Long totalDeaths) { this.totalDeaths = totalDeaths; }

    public Long getTotalRecovered() { return totalRecovered; }
    public void setTotalRecovered(Long totalRecovered) { this.totalRecovered = totalRecovered; }

    public Long getActiveCases() { return activeCases; }
    public void setActiveCases(Long activeCases) { this.activeCases = activeCases; }

    public Double getRecoveryRate() { return recoveryRate; }
    public void setRecoveryRate(Double recoveryRate) { this.recoveryRate = recoveryRate; }

    public Double getMortalityRate() { return mortalityRate; }
    public void setMortalityRate(Double mortalityRate) { this.mortalityRate = mortalityRate; }
}
