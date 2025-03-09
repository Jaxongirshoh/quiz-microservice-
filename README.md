# Quiz Microservice System 🧠📡

## 📌 Overview
The **Quiz Microservice System** is a distributed application designed to manage quizzes. It follows a microservices architecture and includes multiple independent services that handle different functionalities.

## 🏗️ Architecture
This system consists of the following services:

1. **API Gateway** (`api-gateway`) - Manages incoming requests and routes them to the appropriate microservices.
2. **Question Service** (`question-service`) - Handles quiz questions (CRUD operations).
3. **Quiz Service** (`quiz-service`) - Manages quizzes that consist of multiple questions.
4. **Service Registry** (`service-registry`) - Uses **Eureka** to register and discover services dynamically.

---

## 🛠️ Tech Stack
- **Java** (Spring Boot) - Backend
- **Spring Cloud** - Microservices Management
- **PostgreSQL** - Database
- **Eureka Server** - Service Discovery
- **Feign Clients** - Inter-service communication

---


