# 📚 Functional Features & API Reference

This document details the functional capabilities of the **Quiz** and **Student** microservices, including all available API endpoints.

## 🧠 Quiz Service
The core domain service responsible for managing quizzes, questions, and storing results.

### 1. Quiz Management (REST API)
Direct HTTP endpoints for administrators to manage quiz content.

**Base URL**: `/api/quizzes`

| Method | Endpoint | Description | Request Body / Params |
| :--- | :--- | :--- | :--- |
| **POST** | `/` | **Create a new Quiz** | JSON (Title, Category, Difficulty, Description) |
| **GET** | `/` | **List all Quizzes** | None |
| **GET** | `/{id}` | **Get Quiz details** | Path Variable: `id` |
| **GET** | `/category/{cat}` | **Filter by Category** | Path Variable: `cat` (e.g., `Science`) |
| **GET** | `/difficulty/{lvl}`| **Filter by Difficulty**| Path Variable: `lvl` (e.g., `HARD`) |
| **PUT** | `/{id}` | **Update a Quiz** | Path Variable: `id`, JSON Body |
| **DELETE**| `/{id}` | **Delete a Quiz** | Path Variable: `id` |

### 2. Result Management (Internal & gRPC)
*   **Store Results**: Persists student scores with timestamps.
*   **Retrieve Results**: Fetches history of scores for a specific quiz-student pair.

---

## 🎓 Student Service
The edge service acting as a backend-for-frontend (BFF) for students. It manages profiles and handles quiz interactions by communicating with the Quiz Service via gRPC.

### 1. Student Profile Management (REST API)
**Base URL**: `/api/students`

| Method | Endpoint | Description | Request Body / Params |
| :--- | :--- | :--- | :--- |
| **POST** | `/` | **Register Student** | JSON (FirstName, LastName, Email, Grade) |
| **GET** | `/` | **List all Students** | None |
| **GET** | `/{id}` | **Get Student Profile** | Path Variable: `id` |
| **GET** | `/email/{email}` | **Find by Email** | Path Variable: `email` |
| **PUT** | `/{id}` | **Update Profile** | Path Variable: `id`, JSON Body |
| **DELETE**| `/{id}` | **Delete Student** | Path Variable: `id` |

### 2. Quiz Interaction (REST to gRPC)
These endpoints are used by students to take quizzes. The Student Service acts as a proxy, converting these REST requests into gRPC calls to the Quiz Service.

**Base URL**: `/student/quiz`

| Method | Endpoint | Description | Implementation Details |
| :--- | :--- | :--- | :--- |
| **GET** | `/{quizId}` | **Start/Fetch Quiz** | Calls `GetQuiz` gRPC. Returns formatted quiz questions. |
| **POST** | `/{quizId}/submit`| **Submit Answers** | Calls `SubmitResult` gRPC. Params: `studentId`, `score`. Stores result in Quiz DB. |
| **GET** | `/{quizId}/scores`| **Get Score History**| Calls `GetAllResults` gRPC. Returns JSON list of past attempts & timestamps. |

---

## 🚀 Future Features (Roadmap)
These are planned features that extend beyond the current scope:

1.  **Role-Based Access Control (RBAC)**:
    *   Separate `Admin` (CRUD quizzes) and `Student` (Take quizzes) roles.
    *   JWT Authentication via an API Gateway or Keycloak.

2.  **Question Bank & Randomization**:
    *   Instead of fixed questions per quiz, pull `n` random questions from a larger "Question Bank" based on difficulty/category.

3.  **Real-Time Leaderboards**:
    *   Redis-backed leaderboard to show top scores in real-time.

4.  **Analytics Dashboard**:
    *   API to aggregate data: Average class score, hardest question (most wrong answers), and student progress trends.

5.  **Gamification**:
    *   Badges and achievements for completing streaks or difficult quizzes.
