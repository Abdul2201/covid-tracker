package com.abdul.covidtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalStats {
    private long updated;
    private long cases;
    private long todayCases;
    private long deaths;
    private long todayDeaths;
    private long recovered;
    private long todayRecovered;
    private long active;
    private long critical;
    private long tests;
    private long population;
    private double activePerOneMillion;
    private double recoveredPerOneMillion;
    private double criticalPerOneMillion;
    private double testsPerOneMillion;

    public long getUpdated() { return updated; }
    public void setUpdated(long updated) { this.updated = updated; }

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

    public long getTodayRecovered() { return todayRecovered; }
    public void setTodayRecovered(long todayRecovered) { this.todayRecovered = todayRecovered; }

    public long getActive() { return active; }
    public void setActive(long active) { this.active = active; }

    public long getCritical() { return critical; }
    public void setCritical(long critical) { this.critical = critical; }

    public long getTests() { return tests; }
    public void setTests(long tests) { this.tests = tests; }

    public long getPopulation() { return population; }
    public void setPopulation(long population) { this.population = population; }

    public double getActivePerOneMillion() { return activePerOneMillion; }
    public void setActivePerOneMillion(double activePerOneMillion) { this.activePerOneMillion = activePerOneMillion; }

    public double getRecoveredPerOneMillion() { return recoveredPerOneMillion; }
    public void setRecoveredPerOneMillion(double recoveredPerOneMillion) { this.recoveredPerOneMillion = recoveredPerOneMillion; }

    public double getCriticalPerOneMillion() { return criticalPerOneMillion; }
    public void setCriticalPerOneMillion(double criticalPerOneMillion) { this.criticalPerOneMillion = criticalPerOneMillion; }

    public double getTestsPerOneMillion() { return testsPerOneMillion; }
    public void setTestsPerOneMillion(double testsPerOneMillion) { this.testsPerOneMillion = testsPerOneMillion; }
}
