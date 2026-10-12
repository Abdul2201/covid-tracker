package com.abdul.covidtracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "alert_type") // THRESHOLD, TREND, MILESTONE
    private String alertType;
    
    @Column(name = "threshold_value")
    private Long thresholdValue;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "is_read")
    private Boolean isRead = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Alert() {}

    public Alert(Long userId, String country, String alertType, Long thresholdValue, String message) {
        this.userId = userId;
        this.country = country;
        this.alertType = alertType;
        this.thresholdValue = thresholdValue;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }

    public Long getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(Long thresholdValue) { this.thresholdValue = thresholdValue; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
