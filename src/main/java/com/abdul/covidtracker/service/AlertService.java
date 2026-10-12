package com.abdul.covidtracker.service;

import com.abdul.covidtracker.entity.Alert;
import com.abdul.covidtracker.repository.AlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert createAlert(Long userId, String country, String alertType, 
                            Long thresholdValue, String message) {
        Alert alert = new Alert(userId, country, alertType, thresholdValue, message);
        return alertRepository.save(alert);
    }

    public List<Alert> getUserAlerts(Long userId) {
        return alertRepository.findByUserId(userId);
    }

    public List<Alert> getUnreadAlerts(Long userId) {
        return alertRepository.findByUserIdAndIsRead(userId, false);
    }

    public long getUnreadAlertCount(Long userId) {
        return alertRepository.countByUserIdAndIsRead(userId, false);
    }

    public List<Alert> getCountryAlerts(Long userId, String country) {
        return alertRepository.findByUserIdAndCountry(userId, country);
    }

    public Optional<Alert> getAlert(Long alertId) {
        return alertRepository.findById(alertId);
    }

    public Alert markAlertAsRead(Long alertId) throws Exception {
        Optional<Alert> alertOpt = alertRepository.findById(alertId);
        if (alertOpt.isEmpty()) {
            throw new Exception("Alert not found");
        }

        Alert alert = alertOpt.get();
        alert.setIsRead(true);
        return alertRepository.save(alert);
    }

    public void deleteAlert(Long alertId) {
        alertRepository.deleteById(alertId);
    }

    public List<Alert> getUserAlertsOrderByDate(Long userId) {
        return alertRepository.findUserAlertsOrderByCreatedAtDesc(userId);
    }
}
