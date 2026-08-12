# TCO Calculator

A Total Cost of Ownership calculator for vehicles, built as a Spring Boot learning project using Test-Driven Development.

## Status

Early stage. Project scaffold is set up and the first domain-layer component (fuel cost calculation) has been built test-first.

## Stack

- Java 25
- Spring Boot 4.1.0
- Gradle (Kotlin DSL)
- PostgreSQL 16
- Podman (via Spring Boot Docker Compose Support)
- JUnit 5 + AssertJ

## Architecture

Currently a single-module Spring Boot application (`domain` / `service` / `repository` / `controller` package structure). The plan is to feel out Spring's conventions first and refactor toward a hexagonal (ports and adapters) layout once the pain points that architecture solves become concrete rather than theoretical.

## Getting Started

### Prerequisites

- JDK 25
- Podman + `docker-compose` (v2 CLI) configured as the Docker Compose provider

### Running locally

```bash
./gradlew bootRun
```

Spring Boot's Docker Compose Support will automatically start the Postgres container defined in `compose.yaml`.

### Running tests

```bash
./gradlew test
```

## License

MIT — see [LICENSE](LICENSE).