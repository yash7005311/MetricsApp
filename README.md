# Employee Sprint Metrics App

Spring Boot app for **2-week sprint** metrics: employees fill a form per sprint; data is stored in the **employee_metrics** table (H2 database).

## Requirements

- Java 17+
- Maven 3.8+

## How to run

```bash
mvn spring-boot:run
```

- **App:** http://localhost:8080  
- **Frontend (form):** http://localhost:8080  
- **H2 Console:** http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:file:./data/employeesprint`, user: `sa`, password: empty)

## Database

- **H2** file-based DB; data under `./data/employeesprint`.
- **Tables:** `sprint` (2-week periods), `employee_metrics` (one row per employee per sprint).
- On first run, 4 sample sprints are created. Add more via `POST /api/sprints`.

## API

- **Sprints**
  - `GET /api/sprints` — list sprints
  - `POST /api/sprints` — create sprint (body: `name`, `startDate`, `endDate` in ISO format)
- **Metrics**
  - `POST /api/metrics` — submit/update metrics (body: `sprintId`, `employeeId`, `pointsCommitted`, `pointsDelivered`, `bugsCount`, `pullRequestCount`, `spilloverPoints`, `spilloverReason` optional)
  - `GET /api/metrics?sprintId=<id>` — list metrics for a sprint
  - `GET /api/metrics/{employeeId}?sprintId=<id>` — metrics for one employee in a sprint

## Frontend

Open http://localhost:8080 to:

1. **Submit metrics:** choose a sprint, enter employee ID and numeric fields, optional spillover reason, then Submit.
2. **View metrics:** choose a sprint in “View sprint” and see all submitted metrics in a table.

Same employee in the same sprint: submit again to update (upsert).
