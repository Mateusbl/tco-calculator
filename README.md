
# TCO Calculator
 
A Total Cost of Ownership calculator for vehicles, built as a Spring Boot learning project using Test-Driven Development and hexagonal architecture.
 
## Status
 
Core domain and application logic complete. Given a vehicle, the system fetches real FIPE pricing data, projects depreciation via linear regression, and calculates fuel cost, IPVA (vehicle tax), and licensing fees to produce a full TCO estimate. Test coverage sits at 97% (JaCoCo).
 
Not yet built: REST controller (`adapter/in/web`) to expose this as an HTTP API, and a frontend.
 
## Stack
 
- Java 25
- Spring Boot 4.1.0
- Gradle (Kotlin DSL)
- PostgreSQL 16 (via Spring Data JPA)
- Podman (via Spring Boot Docker Compose Support)
- JUnit 5 + AssertJ + Mockito
- JaCoCo (test coverage reporting)
## Architecture
 
Hexagonal (ports and adapters):
 
```
domain/
├── model (Vehicle, FuelType, BrazilianState)
├── FuelCostCalculator, DepreciationCalculator, TcoResult
└── port
    ├── in  (CalculateTcoUseCase)
    └── out (VehiclePricePort, IpvaRatePort, LicensingFeePort)
 
application/
└── service (CalculateTcoService — orchestrates the use case)
 
adapter/
├── in/web        (not yet implemented)
└── out
    ├── fipe        (FipeAdapter — real HTTP client for the Parallelum FIPE API)
    ├── ipva        (StaticIpvaAdapter — rate table by state + fuel type)
    ├── licensing   (StaticLicensingAdapter — fee table by state)
    └── persistence (CachedVehiclePriceAdapter — Postgres-backed cache
                      decorating FipeAdapter to avoid hitting the FIPE
                      API on every request)
```
 
The domain has zero framework dependencies — it only knows about the ports it defines. Every adapter is swappable without touching business logic, and every calculator/service is unit-tested in isolation using mocked ports.
 
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
 
Generates a JaCoCo coverage report at `build/reports/jacoco/test/html/index.html`.
 
## License
 
MIT — see [LICENSE](LICENSE).
 
