# MetroRide API

A Java Spring Boot REST API that provides real-time metro timing information.

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ (for Maven builds)
- Gradle 8.0+ (for Gradle builds)

## 🚀 How to Run

### Using Maven

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR directly:
   ```bash
   java -jar target/metroride-api-1.0.0.jar
   ```

3. **Run tests:**
   ```bash
   mvn test
   ```

### Using Gradle

1. **Build the project:**
   ```bash
   gradle build
   ```

2. **Run the application:**
   ```bash
   gradle bootRun
   ```
   
   Or run the JAR directly:
   ```bash
   java -jar build/libs/metroride-api-1.0.0.jar
   ```

3. **Run tests:**
   ```bash
   gradle test
   ```

## 📡 Sample API Endpoints

The application runs on `http://localhost:8080` by default.

### Get API Information
```
GET /api/metro
```
**Response:**
```json
{
    "service": "MetroRide API",
    "version": "1.0.0",
    "status": "running",
    "currentTime": "14:30"
}
```

### Get All Stations
```
GET /api/metro/stations
```
**Response:**
```json
{
    "stations": [
        {"id": 1, "name": "Central Station", "line": "Blue Line"},
        {"id": 2, "name": "Tech Park", "line": "Blue Line"},
        {"id": 3, "name": "City Mall", "line": "Green Line"},
        {"id": 4, "name": "Airport Terminal", "line": "Red Line"},
        {"id": 5, "name": "University", "line": "Green Line"}
    ],
    "totalStations": 5
}
```

### Get Station Timings
```
GET /api/metro/timings/{stationId}
```
**Example:** `GET /api/metro/timings/1`

**Response:**
```json
{
    "stationId": 1,
    "currentTime": "14:30",
    "upcomingTrains": [
        {"direction": "Northbound", "arrivalIn": "2 mins", "platform": 1},
        {"direction": "Southbound", "arrivalIn": "5 mins", "platform": 2},
        {"direction": "Northbound", "arrivalIn": "8 mins", "platform": 1}
    ]
}
```

### Health Check
```
GET /api/metro/health
```
**Response:**
```json
{
    "status": "UP",
    "message": "MetroRide API is healthy"
}
```

## 📁 Project Structure

```
metroride-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/metroride/
│       │       ├── MetroRideApplication.java
│       │       └── controller/
│       │           └── MetroController.java
│       └── resources/
│           └── application.properties
├── pom.xml              # Maven build configuration
├── build.gradle         # Gradle build configuration
├── settings.gradle      # Gradle settings
├── CONTRIBUTING.md      # Contribution guidelines
├── VERSION.txt          # Version information
└── README.md            # This file
```

## 📖 Version

Current Version: **1.0.0**

See [VERSION.txt](VERSION.txt) for versioning strategy details.

## 🤝 Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## 📄 License

This project is for educational purposes as part of DevOps training.
