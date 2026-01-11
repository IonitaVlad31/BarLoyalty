# BarLoyalty Project

## Run with Docker Compose

1) Create a `.env` file (see `.env.example`).
2) Start services:

```bash
docker compose up --build
```

Default ports (host -> container):
- Gateway: `8082 -> 8080`
- QR Service: `8081 -> 8081`
- Postgres: `5433 -> 5432`
