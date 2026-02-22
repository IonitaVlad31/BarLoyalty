# BarLoyalty

Project with:
- `gateway`: Java + Spring Boot (REST + WebSocket + JPA + Security)
- `qr-service`: C++ service (Crow) that generates QR codes
- `db`: PostgreSQL (via Docker)

## Requirements
### Docker run (recommended)
- Docker Desktop (Windows)

### Local run (without Docker)
- Java 21
- Maven 3.9+
- PostgreSQL (or switch to H2)
- C++ toolchain + CMake (only if running `qr-service` locally)

## Services & ports (default)
- Gateway: `http://localhost:8082`
- QR Service: `http://localhost:8081`
- Postgres: `localhost:5433` (host) \-> `5432` (container)

## Run with Docker Compose
From repo root:

```bash
docker compose up --build
```

Stop:
```bash
docker compose down
```

Reset DB volume (deletes data):
```bash
docker compose down -v
```