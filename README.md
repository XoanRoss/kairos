# Technical Test - Price Management

## Project Overview

Java Spring Boot application for managing and querying product prices by date, brand, and priority. It allows retrieving the applicable price at a given moment, following the specified business rules.


## Technologies Used

- Java 24
- Spring Boot
- Spring Data JPA
- Maven
- H2 Database (already configured in resources, no need for additional setup, just run the application)
- JUnit 5
- Mockito

## Installation and Execution Instructions

1. Clone the repository:

    git clone https://github.com/XoanRoss/kairos.git

2. Build and run the project:

   mvn clean install mvn spring-boot:run

3. To run the tests (results can be found in `target/surefire-reports`):

   mvn test

## Technical Decisions

- An optimized SQL query is used to filter and prioritize prices directly in the database.
- Used hexagonal architecture as defined in the documentation.
- Added only controller and port tests to afford time...

## Contact

- xoanross@gmail.com
- https://github.com/XoanRoss
