# Spring Boot REST application

## Summary

Application is implemented following REST API architectural style using Spring Boot Web project.
Data is persisted in MySQL RDBMS and interaction with it is done via Hibernate ORM using
Spring Boot Data JPA project. Spring Security framework along with JWT are used for authentication and authorization of endpoints.
Project is developed on multiple branches using Git as version control system.
Application is containerized using Docker and image is pushed to repository in DockerHub for further usage during deployment.
For improved code quality and automation of build, test and DockerHub image upload processes is used GitHub Actions
where CI pipelines are run for different branches when code is committed. AWS is used for
deployment (additional information [here](./aws_cdk/README.md)) of the application image from DockerHub.
Kubernetes cluster is used in development environment to run containerized application
so that it can be scaled when needed. In future for deployment of the application in production environment can be
used Amazon Elastic Kubernetes (EKS).

## Tech stack

- Java 17
- Spring Boot 3+
- Spring Security 6
- MySQL
- Docker
- AWS
- Git
- Kubernetes
- CI with GitHub Actions
- React JS

## Running locally

### Prerequisites
- Java 17
- Docker and Docker Compose
- Node.js (for frontend)

### Environment variables

The following environment variables are required. Set them in your shell before running the application:

```env
# MySQL root password (used by Docker to initialize the container)
MYSQL_PASSWORD=<your_mysql_root_password>

# Spring Boot database connection (connects as root — same value as MYSQL_PASSWORD for local dev)
DB_USER=root
DB_PASSWORD=<your_mysql_root_password>
HOST=localhost

# Secret key used to sign and verify JWT tokens (min. 32 characters recommended)
JWT_KEY=<your_jwt_secret>
```

> **Note:** The app connects to MySQL as root for local development. A dedicated application user should be used in production.

In CI/CD these are stored as GitHub Actions secrets.

### 1. Start the database

```bash
docker-compose up mysql
```

### 2. Run the API

```bash
./gradlew bootRun
```

API runs on `http://localhost:8086`.

### 3. Run the frontend

```bash
cd frontend
npm install
npm run dev
```

### Running SonarQube analysis locally

```bash
docker-compose up sonarqube
./gradlew sonar
```

## Domain

The application manages employees and projects within an organization.
Users register to gain access to the API and are each linked to an employee record.
Employees can be assigned to projects, and projects track their current state throughout their lifecycle.

### Entities
```
User        id (UUID), email, password, role (ADMIN | USER)
Employee    id (UUID), name, age
Project     id, name, state (ACTIVE | INACTIVE), createdAt
```

### Relationships

- **User** has one **Employee** (created automatically on registration)
- **Employee** belongs to one **Project**
- **Project** has many **Employees**

## API

All endpoints except `/api/v1/auth/*` require a valid JWT token in the `Authorization: Bearer <token>` header.

Swagger UI is available at `http://localhost:8086/swagger-ui`.

### Authentication
```
POST /api/v1/auth/register     Register a new user
POST /api/v1/auth/login        Login and receive a JWT token
```

### Employees
```
GET  /employees                Get all employees
GET  /employees/oldtest        Get the oldest employee
GET  /employees/{projectId}    Get employees by project
```

### Projects
```
GET  /projects                                          Get all projects
POST /projects                                          Create a project
GET  /projects/{id}                                     Get project by ID
POST /projects/{projectId}/employees/{employeeId}       Add employee to project
```

## CI/CD

GitHub Actions pipelines are configured for two branches:

**dev branch** — runs on every push:
```
build → test
```

**master branch** — runs on every push:
```
build → test → Docker image build → push to DockerHub
```

**SonarQube** — runs on every push to master and on pull requests:
```
build → static code analysis → report to SonarQube
```

Required GitHub Actions secrets:
```
DB_USER          Database username
DB_PASSWORD      Database password
HOST             Database host
JWT_KEY          JWT secret key
SONAR_TOKEN      SonarQube authentication token
SONAR_HOST_URL   SonarQube server URL
DOCKER_USERNAME  DockerHub username
DOCKER_PASSWORD  DockerHub password
```

## Encountered problems

- **Problem:** `@ExceptionHandler(BadCredentialsException.class)` throws `org.springframework.web.HttpMediaTypeNotAcceptableException: No acceptable representation`
  **Solution:** Add getters and setters to the returned DTO in order to be serializable.
