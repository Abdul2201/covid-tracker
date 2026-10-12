# COVID-19 Tracker

A Java Spring Boot application that pulls live COVID-19 statistics from the public disease API and displays them in a responsive dashboard.

## Features
- Global summary cards
- Country table with cases, recovered, and deaths
- Country trend chart (cases and deaths)
- Clean dashboard UI served by Spring Boot + Thymeleaf
- REST API endpoints for frontend consumption

## Tech Stack
- Java 17
- Spring Boot 3.3.x
- Thymeleaf
- HTML/CSS/JavaScript

## Run locally
```bash
mvn spring-boot:run
```

Then open:
```text
http://localhost:8080
```

## API Endpoints
- `GET /api/summary`
- `GET /api/countries`
- `GET /api/history/{country}`

## Notes
This app uses the public Disease.sh API for live data.
