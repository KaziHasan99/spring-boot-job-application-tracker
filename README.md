# Spring Boot Job Application Tracker

A REST API built using Java and Spring Boot to manage job applications and users.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Postman

## Features

- Create, view, update, and delete job applications
- Search applications by company or position
- Pagination and sorting
- Create users
- Manage relationships between users and job applications
- Request validation and exception handling

## Project Structure

- **Controller:** Handles HTTP requests and responses
- **Service:** Contains business logic
- **Repository:** Communicates with the database
- **Model:** Defines database entities
- **DTO:** Defines request and response objects
- **Exception:** Handles application errors

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /applications | Retrieve job applications |
| GET | /applications/{id} | Retrieve an application by ID |
| POST | /applications | Create a job application |
| PUT | /applications/{id} | Update a job application |
| DELETE | /applications/{id} | Delete a job application |
| POST | /users | Create a user |

## Running the Project

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 21 is installed.
4. Run JobtrackerApplication.java.
5. Test the API using Postman at http://localhost:8080.

## Future Improvements

- Complete user management endpoints
- Add PostgreSQL support
- Implement Spring Security and JWT authentication
- Add unit and integration tests
- Build a React frontend

## Author

GitHub: [KaziHasan99](https://github.com/KaziHasan99)