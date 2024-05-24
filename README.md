# Task Tracker Application

This is a RESTful task tracker application built using Spring framework, MySQL database, Flyway for database migrations, JUnit and Mockito for testing, and Swagger for API documentation.

## Features

- **Task Management**: users can create, read, update, and delete tasks.
- **User Management**: create, read, update, and delete users.
- **Swagger Documentation**: API endpoints are documented using Swagger.

## Technologies Used

- Spring Boot
- MySQL
- Flyway
- JUnit
- Mockito
- Swagger

## Setup Instructions

1. Clone the repository
2. Configure the application.properties file with your MySQL database settings.
3. Run the Flyway migration to set up the database schema.
4. Build the project using Maven: mvn clean install.
5. Run the application: java -jar task-tracker.jar.

## API Endpoints

- **GET /tasks**: get all tasks.
- **POST /tasks**: create a new task.
- **GET /tasks/{id}**: get a task by ID.
- **PUT /tasks/{id}**: update a task by ID.
- **DELETE /tasks/{id}**: delete a task by ID.

For more detailed API documentation, access the Swagger UI at: http://localhost:8080/swagger-ui.html