package com.abdul.covidtracker.controller;

import com.abdul.covidtracker.entity.Alert;
import com.abdul.covidtracker.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAlert(@RequestBody AlertRequest request) {
        Alert alert = alertService.createAlert(
                request.getUserId(),
                request.getCountry(),
                request.getAlertType(),
                request.getThresholdValue(),
                request.getMessage()
        );
        return ResponseEntity.ok(new AlertResponse("Alert created successfully", alert));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getUserAlerts(userId));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Alert>> getUnreadAlerts(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getUnreadAlerts(userId));
    }

    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadAlertCount(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getUnreadAlertCount(userId));
    }

    @GetMapping("/user/{userId}/country/{country}")
    public ResponseEntity<List<Alert>> getCountryAlerts(@PathVariable Long userId, @PathVariable String country) {
        return ResponseEntity.ok(alertService.getCountryAlerts(userId, country));
    }

    @PutMapping("/{alertId}/read")
    public ResponseEntity<?> markAlertAsRead(@PathVariable Long alertId) {
        try {
            Alert alert = alertService.markAlertAsRead(alertId);
            return ResponseEntity.ok(new AlertResponse("Alert marked as read", alert));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<ErrorResponse> deleteAlert(@PathVariable Long alertId) {
        alertService.deleteAlert(alertId);
        return ResponseEntity.ok(new ErrorResponse("Alert deleted successfully"));
    }

    // Request and Response classes
    public static class AlertRequest {
        private Long userId;
        private String country;
        private String alertType;
        private Long thresholdValue;
        private String message;

        public AlertRequest() {}

        public Long getUserId() { return userId; }
        public String getCountry() { return country; }
        public String getAlertType() { return alertType; }
        public Long getThresholdValue() { return thresholdValue; }
        public String getMessage() { return message; }
    }

    public static class AlertResponse {
        private String message;
        private Alert alert;

        public AlertResponse(String message, Alert alert) {
            this.message = message;
            this.alert = alert;
        }

        public String getMessage() { return message; }
        public Alert getAlert() { return alert; }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
    }
}
