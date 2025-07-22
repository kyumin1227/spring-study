# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **Spring Boot multi-module application** written in **Kotlin** using Gradle for build management. The project follows a layered architecture with two main modules:

- **module-api**: Web layer containing REST controllers and API-specific services
- **module-common**: Shared business logic, data access layer with JPA entities and repositories

## Key Build Commands

### Building the Application
```bash
./gradlew build          # Build all modules
./gradlew :module-api:build     # Build API module only
./gradlew :module-common:build  # Build common module only
```

### Running the Application
```bash
./gradlew :module-api:bootRun   # Run the Spring Boot application
```

### Testing
```bash
./gradlew test           # Run all tests
./gradlew :module-api:test      # Run API module tests
./gradlew :module-common:test   # Run common module tests
```

### Development
```bash
./gradlew clean          # Clean all build artifacts
./gradlew :module-api:bootJar   # Create executable JAR for API module
```

## Architecture

### Module Dependencies
- **module-api** depends on **module-common** 
- **module-common** is configured as a library module (shared dependency)
- Only **module-api** produces an executable Spring Boot JAR

### Component Structure
```
module-api/
├── controller/         # REST endpoints
├── service/           # API-specific business services  
├── exception/         # Custom exception classes
├── exceptionhandler/ # Global exception handling
└── response/         # Common response structures

module-common/
├── domain/           # JPA entities
├── repositories/     # Data access repositories
├── service/          # Shared business services
└── enums/           # Common enumerations
```

### Database Configuration
- **Database**: MySQL with Spring Data JPA
- **Environment Profiles**: `local`, `beta` configurations available
- **Entity Scanning**: Configured for `dev.be.modulecommon.domain`
- **Repository Scanning**: Configured for `dev.be.modulecommon.repositories`

### Application Startup
The main application class is `ModuleApiApplication.kt` in the module-api module, configured with component scanning across both modules:
- Base packages: `["dev.be.moduleapi", "dev.be.modulecommon"]`

## Development Notes

### Technology Stack
- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.5.3
- **JVM**: Java 17 (configured via toolchain)
- **Build Tool**: Gradle with Kotlin DSL
- **Database**: MySQL with Hibernate/JPA
- **Testing**: JUnit 5 with Kotlin Test

### Configuration Management
- Profile-specific configurations in `application-{profile}.yaml` files
- Global exception handling implemented via `GlobalExceptionHandler`
- Standardized API responses using `CommonResponse` structure