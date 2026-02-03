# Manchester Seals Baseball Stats

Baseball and Team Stats application developed in Java, Spring Boot and MongoDB.

## Overview

This is a RESTful API application for managing baseball teams and players, built with:
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **Java 17**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB 4.0+ (running on localhost:27017, or configure different connection in `application.properties`)

## MongoDB Setup

Make sure MongoDB is running on your local machine:
```bash
# Start MongoDB service (on Linux/Mac)
sudo systemctl start mongod

# Or using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

## Building the Application

```bash
mvn clean package
```

## Running the Application

```bash
# Run with Maven
mvn spring-boot:run

# Or run the JAR file
java -jar target/baseball-stats-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Endpoints

### Teams

- `GET /api/teams` - Get all teams
- `GET /api/teams/{id}` - Get team by ID
- `GET /api/teams/name/{name}` - Get team by name
- `GET /api/teams/league/{league}` - Get teams by league
- `POST /api/teams` - Create a new team
- `PUT /api/teams/{id}` - Update a team
- `DELETE /api/teams/{id}` - Delete a team

#### Team JSON Example:
```json
{
  "name": "Manchester Seals",
  "city": "Manchester",
  "league": "American League",
  "wins": 95,
  "losses": 67
}
```

### Players

- `GET /api/players` - Get all players
- `GET /api/players/{id}` - Get player by ID
- `GET /api/players/team/{teamId}` - Get players by team
- `GET /api/players/position/{position}` - Get players by position
- `POST /api/players` - Create a new player
- `PUT /api/players/{id}` - Update a player
- `DELETE /api/players/{id}` - Delete a player

#### Player JSON Example:
```json
{
  "name": "John Smith",
  "position": "Pitcher",
  "jerseyNumber": 42,
  "teamId": "team-id-here",
  "battingAverage": 0.285,
  "homeRuns": 25,
  "runs": 87
}
```

## Configuration

MongoDB connection can be configured in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/baseball_stats
spring.data.mongodb.database=baseball_stats
server.port=8080
```

## Testing

Run tests with:
```bash
mvn test
```

## Project Structure

```
src/
├── main/
│   ├── java/com/manchesterseals/baseball/
│   │   ├── BaseballStatsApplication.java    # Main application class
│   │   ├── controller/
│   │   │   ├── PlayerController.java        # Player REST endpoints
│   │   │   └── TeamController.java          # Team REST endpoints
│   │   ├── model/
│   │   │   ├── Player.java                  # Player entity
│   │   │   └── Team.java                    # Team entity
│   │   └── repository/
│   │       ├── PlayerRepository.java        # Player MongoDB repository
│   │       └── TeamRepository.java          # Team MongoDB repository
│   └── resources/
│       └── application.properties           # Application configuration
└── test/
    └── java/com/manchesterseals/baseball/
        └── BaseballStatsApplicationTests.java
```
