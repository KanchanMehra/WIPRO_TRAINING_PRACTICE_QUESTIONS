package com.metroride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MetroRide Application - Real-time Metro Timing Service
 * A Spring Boot REST API for metro timing information
 */
@SpringBootApplication
public class MetroRideApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetroRideApplication.class, args);
    }
}
