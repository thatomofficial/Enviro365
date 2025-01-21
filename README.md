# Waste Sorting Application

## Overview

The Waste Sorting Application is a Spring Boot-based project designed to manage waste categories, disposal guidelines, and recycling tips. It provides RESTful APIs for CRUD operations and search functionalities.

## Features

- Manage waste categories
- Manage disposal guidelines
- Manage recycling tips
- Search and filter functionalities
- API documentation with Swagger

## Technologies Used

- Java 17
- Spring Boot 3.1.3
- Spring Data JPA
- H2 Database
- Maven
- Swagger/OpenAPI

## Getting Started

### Prerequisites

- Java 17
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone git@github.com:thatomofficial/Enviro365.git
    cd waste-sorting-app
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Accessing the Application

- The application will be available at `http://localhost:8080`.
- The H2 database console can be accessed at `http://localhost:8080/h2-console`.
- Swagger API documentation can be accessed at `http://localhost:8080/swagger-ui.html`.

## API Endpoints

### Waste Categories

- `GET /api/v1/waste-categories`: Get all waste categories
- `GET /api/v1/waste-categories/page`: Get waste categories page
- `GET /api/v1/waste-categories/sorted`: Get waste categories sorted
- `GET /api/v1/waste-categories/filter`: Get waste categories filtered
- `GET /api/v1/waste-categories/search`: Search waste categories
- `GET /api/v1/waste-categories/{id}`: Get waste category by ID
- `POST /api/v1/waste-categories`: Create a new waste category
- `PUT /api/v1/waste-categories/{id}`: Update waste category by ID
- `DELETE /api/v1/waste-categories/{id}`: Delete waste category by ID

### Disposal Guidelines

- `GET /api/v1/disposal-guidelines`: Get all disposal guidelines
- `GET /api/v1/disposal-guidelines/page`: Get disposal guidelines page
- `GET /api/v1/disposal-guidelines/sorted`: Get disposal guidelines sorted
- `GET /api/v1/disposal-guidelines/filter`: Get disposal guidelines filtered
- `GET /api/v1/disposal-guidelines/search`: Search disposal guidelines
- `GET /api/v1/disposal-guidelines/{id}`: Get disposal guideline by ID
- `POST /api/v1/disposal-guidelines`: Create a new disposal guideline
- `PUT /api/v1/disposal-guidelines/{id}`: Update disposal guideline by ID
- `DELETE /api/v1/disposal-guidelines/{id}`: Delete disposal guideline by ID

### Recycling Tips

- `GET /api/v1/recycling-tips`: Get all recycling tips
- `GET /api/v1/recycling-tips/page`: Get recycling tips page
- `GET /api/v1/recycling-tips/sorted`: Get recycling tips sorted
- `GET /api/v1/recycling-tips/filter`: Get recycling tips filtered
- `GET /api/v1/recycling-tips/search`: Search recycling tips
- `GET /api/v1/recycling-tips/{id}`: Get recycling tip by ID
- `POST /api/v1/recycling-tips`: Create a new recycling tip
- `PUT /api/v1/recycling-tips/{id}`: Update recycling tip by ID
- `DELETE /api/v1/recycling-tips/{id}`: Delete recycling tip by ID

## Database

The application uses an in-memory H2 database. The schema and initial data are defined in the `src/main/resources/schema.sql` and `src/main/resources/data.sql` files, respectively.

## Configuration

The application configuration is defined in the `src/main/resources/application.properties` file.
