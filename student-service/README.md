# Student Service

A Spring Boot microservice for managing student profiles and quiz attempts in a quiz platform.

## Overview

The Student Service provides RESTful APIs for student registration, profile management, and tracking quiz attempt records.

## Technology Stack

- **Spring Boot**: 3.2.1
- **Java**: 17
- **Database**: H2 (in-memory)
- **Build Tool**: Maven
- **Port**: 8082

## Features

- CRUD operations for student profiles
- Email-based student lookup with duplicate prevention
- Quiz attempt tracking and history
- Student performance analytics by quiz or student
- Input validation with detailed error responses
- Global exception handling
- H2 console for database inspection

## Project Structure

```
student-service/
├── src/main/java/com/quizplatform/student/
│   ├── StudentServiceApplication.java        # Main application class
│   ├── controller/
│   │   ├── StudentController.java            # Student REST endpoints
│   │   └── QuizAttemptController.java        # Quiz attempt REST endpoints
│   ├── service/
│   │   ├── StudentService.java               # Student service interface
│   │   ├── StudentServiceImpl.java           # Student service implementation
│   │   ├── QuizAttemptService.java           # Quiz attempt service interface
│   │   └── QuizAttemptServiceImpl.java       # Quiz attempt service implementation
│   ├── repository/
│   │   ├── StudentRepository.java            # Student data access layer
│   │   └── QuizAttemptRepository.java        # Quiz attempt data access layer
│   ├── model/
│   │   ├── Student.java                      # Student entity
│   │   └── QuizAttempt.java                  # Quiz attempt entity
│   ├── dto/
│   │   ├── StudentDTO.java                   # Student data transfer object
│   │   └── QuizAttemptDTO.java               # Quiz attempt data transfer object
│   └── exception/
│       ├── GlobalExceptionHandler.java       # Exception handling
│       ├── StudentNotFoundException.java     # Custom exception
│       ├── QuizAttemptNotFoundException.java # Custom exception
│       └── DuplicateEmailException.java      # Custom exception
└── src/main/resources/
    └── application.yml                        # Application configuration
```

## API Endpoints

### Student Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/students` | Register a new student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/email/{email}` | Get student by email |
| PUT | `/api/students/{id}` | Update student profile |
| DELETE | `/api/students/{id}` | Delete student |

### Quiz Attempt Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/quiz-attempts` | Record a quiz attempt |
| GET | `/api/quiz-attempts` | Get all quiz attempts |
| GET | `/api/quiz-attempts/{id}` | Get attempt by ID |
| GET | `/api/quiz-attempts/student/{studentId}` | Get all attempts by student |
| GET | `/api/quiz-attempts/quiz/{quizId}` | Get all attempts for a quiz |
| DELETE | `/api/quiz-attempts/{id}` | Delete an attempt |

## Data Models

### Student
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "enrollmentDate": "2026-02-05T16:35:00",
  "grade": "A"
}
```

### Quiz Attempt
```json
{
  "id": 1,
  "studentId": 1,
  "quizId": 1,
  "score": 85.5,
  "attemptDate": "2026-02-05T16:35:00",
  "timeTaken": 300
}
```

## Running the Service

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Navigate to the service directory
cd student-service

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The service will start on `http://localhost:8082`

### Access H2 Console

Visit `http://localhost:8082/h2-console`

- **JDBC URL**: `jdbc:h2:mem:studentdb`
- **Username**: `sa`
- **Password**: (leave empty)

## Example Usage

### Register a Student

```bash
curl -X POST http://localhost:8082/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "grade": "A"
  }'
```

### Record a Quiz Attempt

```bash
curl -X POST http://localhost:8082/api/quiz-attempts \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "quizId": 1,
    "score": 85.5,
    "timeTaken": 300
  }'
```

### Get Student's Quiz History

```bash
curl http://localhost:8082/api/quiz-attempts/student/1
```

## Configuration

Key configurations in `application.yml`:

- **Server Port**: 8082
- **Database**: H2 in-memory
- **JPA**: Auto DDL update enabled
- **Logging**: DEBUG level for student package

## Validation Rules

### Student
- First name and last name are required
- Email must be valid and unique
- Grade is optional

### Quiz Attempt
- Student ID and Quiz ID are required
- Score must be positive
- Time taken is optional

## Error Handling

The service provides structured error responses:

```json
{
  "status": 404,
  "message": "Student not found with id: 1",
  "timestamp": "2026-02-05T16:35:00"
}
```

For duplicate emails:
```json
{
  "status": 409,
  "message": "Email already exists: john.doe@example.com",
  "timestamp": "2026-02-05T16:35:00"
}
```

## Future Enhancements

- Integration with Quiz Service for quiz validation
- Student analytics and performance metrics
- Certificate generation for completed quizzes
- Email notifications for quiz results
- Integration with Eureka for service discovery
- JWT-based authentication
