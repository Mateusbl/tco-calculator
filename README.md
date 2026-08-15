# TCO Calculator
 
A Total Cost of Ownership calculator for vehicles, built as a Spring Boot learning project using Test-Driven Development and hexagonal architecture.
 
## Status
 
Core domain and application logic complete, and exposed over HTTP. Given a vehicle (brand/model/year codes + state), the system fetches real FIPE pricing data, looks up fuel consumption by scraping the latest Inmetro efficiency PDF, projects depreciation via linear regression, and calculates fuel cost, IPVA (vehicle tax), and licensing fees to produce a full TCO estimate.

`POST /tco/calcular` — single endpoint, takes `brandCode`, `modelCode`, `yearCode`, `state`, `kmPerYear`, `pricePerLiter`, returns the full cost breakdown plus total.

Domain, calculators, and the FIPE/IPVA/licensing/persistence adapters are unit-tested. The web controller, `BuildVehicleService`, and `FuelConsumptionService` (the Inmetro-backed fuel consumption port) don't have tests yet.

Not yet built: frontend.
 
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
├── model (Vehicle, VehicleDetails, FuelType, BrazilianState)
├── VehicleFactory, FuelCostCalculator, DepreciationCalculator, TcoResult
└── port
    ├── in  (BuildVehicleUseCase, CalculateTcoUseCase)
    └── out (VehiclePricePort, FuelConsumptionPort, IpvaRatePort, LicensingFeePort)
 
application/
└── service
    ├── BuildVehicleService     (assembles a Vehicle from FIPE details + fuel consumption)
    ├── CalculateTcoService     (orchestrates the TCO calculation)
    └── FuelConsumptionService  (FuelConsumptionPort impl, backed by InmetroAdapter)
 
adapter/
├── in/web         (TcoController — exposes POST /tco/calcular)
└── out
    ├── fipe        (FipeAdapter — real HTTP client for the Parallelum FIPE API)
    ├── inmetro     (InmetroAdapter — downloads the latest Inmetro fuel-efficiency
                      PDF from gov.br, extracts text with PDFBox, and parses
                      consumption by make/model)
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

### Example request

```bash
curl -X POST http://localhost:8080/tco/calcular \
  -H "Content-Type: application/json" \
  -d '{
    "brandCode": 22,
    "modelCode": 5940,
    "yearCode": "2021-1",
    "state": "RJ",
    "kmPerYear": 15000,
    "pricePerLiter": 6.10
  }'
```
 
## License
 
MIT — see [LICENSE](LICENSE).