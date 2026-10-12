package com.abdul.covidtracker.repository;

import com.abdul.covidtracker.entity.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, Long> {
    
    Optional<CovidData> findByCountry(String country);
    
    Optional<CovidData> findByCountryCode(String countryCode);
    
    List<CovidData> findByContinent(String continent);
    
    @Query("SELECT c FROM CovidData c ORDER BY c.totalCases DESC LIMIT 10")
    List<CovidData> findTop10ByTotalCases();
    
    @Query("SELECT c FROM CovidData c WHERE c.activeCases > :threshold ORDER BY c.activeCases DESC")
    List<CovidData> findCountriesWithHighActiveCases(@Param("threshold") Long threshold);
    
    @Query("SELECT c FROM CovidData c ORDER BY c.mortalityRate DESC LIMIT 10")
    List<CovidData> findTop10ByMortalityRate();
    
    @Query("SELECT COUNT(c) FROM CovidData c")
    long countTotalCountries();
    
    @Query("SELECT SUM(c.totalCases) FROM CovidData c")
    Long getTotalGlobalCases();
    
    @Query("SELECT SUM(c.totalDeaths) FROM CovidData c")
    Long getTotalGlobalDeaths();
    
    @Query("SELECT SUM(c.totalRecovered) FROM CovidData c")
    Long getTotalGlobalRecovered();
}
