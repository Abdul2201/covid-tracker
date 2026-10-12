package com.abdul.covidtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryStats {
    private String country;
    private String continent;
    private long cases;
    private long todayCases;
    private long deaths;
    private long todayDeaths;
    private long recovered;
    private long active;
    private long critical;
    private long tests;
    private long population;
    private CountryInfo countryInfo;

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }

    public long getCases() { return cases; }
    public void setCases(long cases) { this.cases = cases; }

    public long getTodayCases() { return todayCases; }
    public void setTodayCases(long todayCases) { this.todayCases = todayCases; }

    public long getDeaths() { return deaths; }
    public void setDeaths(long deaths) { this.deaths = deaths; }

    public long getTodayDeaths() { return todayDeaths; }
    public void setTodayDeaths(long todayDeaths) { this.todayDeaths = todayDeaths; }

    public long getRecovered() { return recovered; }
    public void setRecovered(long recovered) { this.recovered = recovered; }

    public long getActive() { return active; }
    public void setActive(long active) { this.active = active; }

    public long getCritical() { return critical; }
    public void setCritical(long critical) { this.critical = critical; }

    public long getTests() { return tests; }
    public void setTests(long tests) { this.tests = tests; }

    public long getPopulation() { return population; }
    public void setPopulation(long population) { this.population = population; }

    public CountryInfo getCountryInfo() { return countryInfo; }
    public void setCountryInfo(CountryInfo countryInfo) { this.countryInfo = countryInfo; }
}
