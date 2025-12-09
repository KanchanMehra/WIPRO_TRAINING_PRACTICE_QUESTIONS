package com.metroride.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for Metro Timing Information
 * Provides real-time metro schedules and station information
 */
@RestController
@RequestMapping("/api/metro")
public class MetroController {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Get welcome message and API status
     */
    @GetMapping
    public Map<String, Object> getApiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "MetroRide API");
        response.put("version", "1.0.0");
        response.put("status", "running");
        response.put("currentTime", LocalTime.now().format(TIME_FORMATTER));
        return response;
    }

    /**
     * Get all metro stations
     */
    @GetMapping("/stations")
    public Map<String, Object> getAllStations() {
        Map<String, Object> response = new HashMap<>();
        response.put("stations", List.of(
            Map.of("id", 1, "name", "Central Station", "line", "Blue Line"),
            Map.of("id", 2, "name", "Tech Park", "line", "Blue Line"),
            Map.of("id", 3, "name", "City Mall", "line", "Green Line"),
            Map.of("id", 4, "name", "Airport Terminal", "line", "Red Line"),
            Map.of("id", 5, "name", "University", "line", "Green Line")
        ));
        response.put("totalStations", 5);
        return response;
    }

    /**
     * Get metro timings for a specific station
     */
    @GetMapping("/timings/{stationId}")
    public Map<String, Object> getStationTimings(@PathVariable int stationId) {
        Map<String, Object> response = new HashMap<>();
        response.put("stationId", stationId);
        response.put("currentTime", LocalTime.now().format(TIME_FORMATTER));
        
        // Sample upcoming train timings
        LocalTime now = LocalTime.now();
        response.put("upcomingTrains", List.of(
            Map.of("direction", "Northbound", "arrivalIn", "2 mins", "platform", 1),
            Map.of("direction", "Southbound", "arrivalIn", "5 mins", "platform", 2),
            Map.of("direction", "Northbound", "arrivalIn", "8 mins", "platform", 1)
        ));
        
        return response;
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "MetroRide API is healthy");
        return response;
    }
}
