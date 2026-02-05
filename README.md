# Quiz Platform Microservices

A microservices-based quiz platform built with Spring Boot, featuring separate services for quiz management and student management.

## Architecture Overview

This project implements a microservices architecture with two independent services:

```
Quiz Platform
├── quiz-service (Port 8081)
│   └── Manages quizzes and questions
└── student-service (Port 8082)
    └── Manages students and quiz attempts
```

## Services

### 1. Quiz Service (Port 8081)
Handles quiz and question management with the following features:
- Create, read, update, and delete quizzes
- Support for multiple questions per quiz
- Category and difficulty-based filtering
- Question options and correct answer tracking

**Key Endpoints:**
- `POST /api/quizzes` - Create quiz
- `GET /api/quizzes` - Get all quizzes
- `GET /api/quizzes/{id}` - Get quiz by ID
- `GET /api/quizzes/category/{category}` - Filter by category

[📖 Quiz Service Documentation](./quiz-service/README.md)

### 2. Student Service (Port 8082)
Manages student profiles and quiz attempt records with the following features:
- Student registration and profile management
- Email-based lookup with duplicate prevention
- Quiz attempt tracking and history
- Performance analytics per student or quiz

**Key Endpoints:**
- `POST /api/students` - Register student
- `GET /api/students` - Get all students
- `POST /api/quiz-attempts` - Record quiz attempt
- `GET /api/quiz-attempts/student/{studentId}` - Get student's attempts

[📖 Student Service Documentation](./student-service/README.md)

## Technology Stack

| Technology | Version |
|------------|---------|
| Spring Boot | 3.2.1 |
| Java | 17 |
| Database | H2 (in-memory) |
| Build Tool | Maven |
| ORM | Spring Data JPA |

## Project Structure

```
Microservices_PE/
├── quiz-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/quizplatform/quiz/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── model/
│   │   │   │   ├── dto/
│   │   │   │   └── exception/
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   ├── pom.xml
│   └── README.md
│
└── student-service/
    ├── src/
    │   ├── main/
    │   │   ├── java/com/quizplatform/student/
    │   │   │   ├── controller/
    │   │   │   ├── service/
    │   │   │   ├── repository/
    │   │   │   ├── model/
    │   │   │   ├── dto/
    │   │   │   └── exception/
    │   │   └── resources/
    │   │       └── application.yml
    │   └── test/
    ├── pom.xml
    └── README.md
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Services

**Option 1: Run each service individually**

```bash
# Terminal 1 - Start Quiz Service
cd quiz-service
mvn spring-boot:run

# Terminal 2 - Start Student Service
cd student-service
mvn spring-boot:run
```

**Option 2: Build and run JAR files**

```bash
# Build both services
cd quiz-service && mvn clean package && cd ..
cd student-service && mvn clean package && cd ..

# Run the services
java -jar quiz-service/target/quiz-service-1.0.0.jar &
java -jar student-service/target/student-service-1.0.0.jar &
```

### Verify Services are Running

```bash
# Check Quiz Service
curl http://localhost:8081/api/quizzes

# Check Student Service
curl http://localhost:8082/api/students
```

## Database Access

Both services use H2 in-memory databases with web consoles:

- **Quiz Service**: http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:quizdb`
  - Username: `sa`
  - Password: (empty)

- **Student Service**: http://localhost:8082/h2-console
  - JDBC URL: `jdbc:h2:mem:studentdb`
  - Username: `sa`
  - Password: (empty)

## Quick Example Workflow

### 1. Create a Quiz

```bash
curl -X POST http://localhost:8081/api/quizzes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Java Fundamentals",
    "description": "Test your Java knowledge",
    "category": "Programming",
    "difficulty": "EASY",
    "questions": [
      {
        "questionText": "What is polymorphism?",
        "options": ["Method overloading", "Method overriding", "Both", "Neither"],
        "correctAnswer": "Both",
        "points": 10
      }
    ]
  }'
```

### 2. Register a Student

```bash
curl -X POST http://localhost:8082/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Smith",
    "email": "alice.smith@example.com",
    "grade": "A"
  }'
```

### 3. Record a Quiz Attempt

```bash
curl -X POST http://localhost:8082/api/quiz-attempts \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "quizId": 1,
    "score": 90.0,
    "timeTaken": 180
  }'
```

### 4. View Student's Quiz History

```bash
curl http://localhost:8082/api/quiz-attempts/student/1
```

## Key Design Patterns

### 1. **Database per Service**
Each microservice has its own database (quizdb and studentdb), ensuring loose coupling.

### 2. **Layered Architecture**
- **Controller Layer**: REST endpoints and request handling
- **Service Layer**: Business logic
- **Repository Layer**: Data persistence
- **DTO Layer**: Data transfer objects for API contracts

### 3. **Exception Handling**
Global exception handlers provide consistent error responses across all services.

### 4. **Validation**
Jakarta Bean Validation annotations ensure data integrity at the API boundary.

## Future Roadmap (Service Mesh Features)

Phase 2 will introduce service mesh capabilities:

- [ ] **Service Discovery** - Eureka Server for dynamic service registration
- [ ] **API Gateway** - Spring Cloud Gateway for routing and load balancing
- [ ] **Circuit Breaker** - Resilience4j for fault tolerance
- [ ] **Distributed Tracing** - Sleuth and Zipkin for request tracking
- [ ] **Centralized Configuration** - Spring Cloud Config Server
- [ ] **Inter-service Communication** - REST/gRPC between services
- [ ] **Authentication** - JWT-based security with OAuth2

## Common Issues and Solutions

### Port Already in Use
```bash
# Kill process on port 8081 or 8082
lsof -ti:8081 | xargs kill -9
lsof -ti:8082 | xargs kill -9
```

### Maven Build Fails
```bash
# Clean and rebuild
mvn clean install -U
```

### H2 Console Not Accessible
Ensure the service is running and check `application.yml` has:
```yaml
spring:
  h2:
    console:
      enabled: true
```

## Contributing

When adding new features:
1. Follow the existing package structure
2. Add appropriate DTOs for API contracts
3. Implement service interfaces before implementations
4. Add global exception handling for new exceptions
5. Update README documentation

## License

This project is created for educational purposes.
