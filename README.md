# Movies Platform

A RESTful backend application for managing movies, actors, genres, user ratings, and personal watchlists.

The application provides movie search and filtering, pagination and sorting, JWT-based authentication, and role-based access control for administrative operations.

## Features

### Movie Management
- Create, update, delete, and retrieve movies.
- Search movies by title.
- Filter movies by genre, release date range, and minimum average rating.
- Pagination and sorting.

### Actors and Genres
- Manage actors and genres.
- Associate movies with multiple actors and genres.

### Ratings
- Rate movies on a scale from 1 to 5.
- Allow each user to rate a movie once and update their rating.
- Display the average rating and total number of ratings for each movie.

### Watchlist
- Add movies to a personal watchlist.
- Retrieve the authenticated user's watchlist.
- Remove movies from the watchlist.
- Prevent duplicate entries.

### Authentication and Authorization
- User registration and login.
- JWT-based authentication.
- Token invalidation on logout.
- Role-based access control for administrative operations.

### API Documentation
- Interactive API documentation using Swagger UI.
- JWT Bearer authentication support in Swagger UI.

## Technologies Used

### Backend
- Java
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security

### Database
- MySQL

### Authentication and Security
- JSON Web Tokens (JWT)
- Spring Security
- BCrypt Password Encoding

### API Documentation
- OpenAPI
- Swagger UI

### Build and Dependency Management
- Apache Maven

### Data Validation
- Jakarta Bean Validation

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- MySQL Server
- Git

### Installation

1. Clone the repository:

   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:

   ```bash
   cd moviesplatform
   ```

3. Create a MySQL database for the application.

4. Configure the database connection and JWT secret in your application configuration.

5. Build the application:

   ```bash
   mvn clean package
   ```

6. Run the application:

   ```bash
   mvn spring-boot:run
   ```


## API Documentation

The project provides interactive API documentation through Swagger UI.

After starting the application, open(depending on default settings, you can write the port you have used if it is not 8080):

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

### Authentication in Swagger UI

1. Register a new account or log in using the authentication endpoints.
2. Copy the JWT token returned by the login endpoint.
3. Click **Authorize** in Swagger UI.
4. Enter your JWT token in the authorization field.
5. Execute secured endpoints using the authenticated session.

Some endpoints require authentication, while administrative operations require the appropriate role.


## Project Structure

The project follows a layered architecture to separate responsibilities and improve maintainability.

```text
src/
└── main/
    ├── java/
    │   └── com.example.moviesplatform/
    │       ├── config/
    │       ├── controller/
    │       ├── dto/
    │       ├── entity/
    │       ├── exception/
    │       ├── repository/
    │       ├── security/
    │       └── service/
    └── resources/
        └── application.properties
```

### Main Layers

- **Controller:** Handles HTTP requests and returns API responses.
- **Service:** Contains business logic and coordinates application operations.
- **Repository:** Handles database access using Spring Data JPA.
- **Entity:** Represents the application's database entities.
- **DTO:** Defines the data exchanged between clients and the API.
- **Security:** Contains authentication and authorization components.
- **Config:** Contains application configuration.
- **Exception:** Contains custom exceptions and global exception handling.

## Configuration

Before running the application, configure the required environment variables for your local environment.

The application requires database connection settings and a JWT secret.

| Environment Variable | Description |
|---|---|
| `DB_URL` | MySQL database connection URL |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `JWT_SECRET` | Secret key used for JWT operations |

Make sure to provide the required values in your environment or local configuration.

**Security Note:** Never commit database credentials, JWT secrets, or other sensitive configuration values to version control.
