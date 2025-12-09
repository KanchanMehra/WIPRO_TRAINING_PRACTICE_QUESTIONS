# MetroRide API - CI/CD Pipeline Documentation

This document explains the CI/CD pipeline setup, Docker containerization, and orchestration for the MetroRide API project.

---

## Table of Contents
1. [Jenkinsfile Stages](#jenkinsfile-stages)
2. [Docker Commands Used](#docker-commands-used)
3. [How to Run Docker Compose](#how-to-run-docker-compose)
4. [Health Check Script](#health-check-script)

---

## Jenkinsfile Stages

The Jenkins pipeline consists of the following stages:

| Stage | Description |
|-------|-------------|
| **Checkout** | Checks out the source code from the SCM repository |
| **Maven Build** | Builds the project using `mvn clean package -DskipTests` |
| **Archive JAR** | Archives the built JAR file as a build artifact |
| **Docker Login** | Authenticates with the Docker registry |
| **Docker Build** | Builds the Docker image from the Dockerfile |
| **Docker Push** | Pushes the built image to the Docker registry |

### Pipeline Flow
```
Checkout → Maven Build → Archive JAR → Docker Login → Docker Build → Docker Push
```

---

## Docker Commands Used

### Building the Docker Image
```bash
docker build -t metroride-api:latest .
```

### Running the Container
```bash
docker run -d -p 8080:8080 --name metroride-app metroride-api:latest
```

### Viewing Running Containers
```bash
docker ps
```

### Viewing Container Logs
```bash
docker logs metroride-app
```

### Stopping the Container
```bash
docker stop metroride-app
```

### Removing the Container
```bash
docker rm metroride-app
```

### Pushing to Registry
```bash
docker push docker.io/metroride-api:latest
```

---

## How to Run Docker Compose

### Prerequisites
- Docker installed
- Docker Compose installed

### Starting All Services
```bash
docker-compose up -d
```

### Starting with Build
```bash
docker-compose up --build -d
```

### Viewing Service Logs
```bash
docker-compose logs -f
```

### Viewing Specific Service Logs
```bash
docker-compose logs -f app
docker-compose logs -f monitor
```

### Stopping All Services
```bash
docker-compose down
```

### Stopping and Removing Volumes
```bash
docker-compose down -v
```

### Services Included
| Service | Description | Port |
|---------|-------------|------|
| **app** | MetroRide API application | 8080 |
| **monitor** | BusyBox container running ping | N/A |

---

## Health Check Script

The `health_check.sh` script performs basic system health checks.

### Making the Script Executable
```bash
chmod +x health_check.sh
```

### Running the Script
```bash
./health_check.sh
```

### What It Checks
- **Disk Usage**: Shows disk space utilization using `df -h`
- **Memory Usage**: Displays memory statistics using `free -h`
- **Java Processes**: Lists all running Java processes using `ps aux | grep java`

---

## Quick Start Guide

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd metroride-api
   ```

2. **Build with Maven**
   ```bash
   mvn clean package
   ```

3. **Build Docker Image**
   ```bash
   docker build -t metroride-api:latest .
   ```

4. **Run with Docker Compose**
   ```bash
   docker-compose up -d
   ```

5. **Access the Application**
   - API: http://localhost:8080

---

## Project Structure
```
metroride-api/
├── Jenkinsfile           # CI/CD pipeline definition
├── Dockerfile            # Container build instructions
├── docker-compose.yml    # Multi-service orchestration
├── health_check.sh       # System health check script
├── pom.xml               # Maven project configuration
├── src/                  # Source code
└── README.md             # This documentation file
```



