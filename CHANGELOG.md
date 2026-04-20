# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## API

### [Unreleased]

### [1.2.0] - 2025-05-30

#### Added
- SonarQube GitHub Actions workflow for static code analysis on master
- JaCoCo code coverage configuration in Gradle
- Multi-stage Docker image using Alpine for reduced image size
- `addEmployeeToProject()` in `ProjectService`

#### Changed
- Project restructured — Spring Boot, K8s manifests, AWS CDK, and `docker-compose.yaml` moved to repository root
- README updated with summary and tech stack sections

#### Infrastructure
- SonarQube `projectKey` property added in `build.gradle`

### [1.1.0] - 2025-03-11

#### Added
- `POST /projects` endpoint for project creation
- `GET /projects` endpoint
- `ProjectEntity`, `ProjectState` enum, `ProjectCreateDto`, `ProjectInfoDto`
- `createdAt` field on `ProjectEntity`
- `addEmployeeToProject()` in `ProjectService`
- `UserRole` enumeration

#### Changed
- `UserEntity` primary key changed from Long to UUID
- `UserDetails` implementation extracted to dedicated class
- CORS port sourced from `application.properties`

#### Fixed
- `POST /login` — `UserDetails` implementation correctly embedded in JWT
- 403 error for authenticated users — `doFilter()` call added in JWT filter
- Join table name corrected in `EmployeeEntity`

### [1.0.0] - 2024-12-30

#### Added
- `EmployeeEntity` with JPA mapping and `EmployeeService` / `EmployeeServiceImpl`
- `GET /employees` and `GET /employees/{id}` endpoints
- `getOldestEmployee()` — query for oldest employee
- `GET /projects` endpoint with `ProjectEntity` and `ProjectInfoDto`
- `getAllEmployeesByProject()` — query employees by project
- JWT-based authentication with `POST /register` and `POST /login`
- Spring Security configuration with CORS support
- `JwtService` for token generation and validation
- `AuthenticationService` with register and login flows
- `ModelMapper` bean for DTO/entity mapping
- `UserRole` enumeration
- Dockerfile for containerized builds
- Kubernetes `Deployment` and `Service` manifests
- GitHub Actions CI pipeline — dev branch (build + test), master branch (build + test + Docker push to DockerHub)
- AWS CDK stack with Fargate containers behind an Elastic Load Balancer
- `docker-compose.yaml` with SonarQube and PostgreSQL for local analysis
- JaCoCo code coverage report generation after test execution
- SonarQube Gradle plugin with credentials from environment variables
- Initial application test

#### Infrastructure
- Gradle build configured to produce a single JAR
- Artifact sharing between CI pipeline jobs via `upload-artifact` / `download-artifact`
- Docker image published to `knetsov91/spring-boot-rest` on DockerHub

---

## UI

### [Unreleased]

### [1.0.0] - 2025-03-10

#### Added
- React JS frontend (Vite-based)
