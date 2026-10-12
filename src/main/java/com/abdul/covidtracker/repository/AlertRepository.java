package com.abdul.covidtracker.repository;

import com.abdul.covidtracker.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
    List<Alert> findByUserId(Long userId);
    
    List<Alert> findByUserIdAndIsRead(Long userId, Boolean isRead);
    
    List<Alert> findByUserIdAndCountry(Long userId, String country);
    
    @Query("SELECT a FROM Alert a WHERE a.userId = :userId ORDER BY a.createdAt DESC")
    List<Alert> findUserAlertsOrderByCreatedAtDesc(@Param("userId") Long userId);
    
    long countByUserIdAndIsRead(Long userId, Boolean isRead);
}
