# Backend Wagon Tracker

This is a Spring Boot application used to track wagons as they move between departments. The project is built with Java 17 and Maven.

## Features
- Manage **Wagons**, **Departments**, and **UHF Readers**
- Log wagon movements and query history
- REST endpoints under `/api` returning JSON
- MySQL database configuration in `application.properties`

## Building
Ensure you have Java 17 and Maven installed, then run:

```bash
mvn clean package
```

## Running
Edit `src/main/resources/application.properties` to match your MySQL credentials and run:

```bash
mvn spring-boot:run
```

The application listens on port `9090` by default.

## API Overview
### Wagons
- `GET /api/wagons` – list all wagons
- `GET /api/wagons/tag/{tagId}` – get a wagon by tag ID
- `PUT /api/wagons/{wagonId}/tag?newTagId=...` – update a wagon's tag
- `POST /api/wagons` – create a wagon

### Departments
- `GET /api/departments` – list departments
- `GET /api/departments/{name}` – get department by name
- `POST /api/departments` – create a department

### UHF Readers
- `GET /api/readers` – list all readers
- `GET /api/readers/{readerId}` – get reader by ID
- `POST /api/readers` – create a reader

### History Tracker
- `POST /api/history-tracker/log` – log wagon activity
- `GET /api/history-tracker` – list history entries
- `GET /api/history-tracker/filter` – filter history by wagon, department, and time
- `GET /api/history-tracker/latestByWagon` – latest entry per wagon

## Testing
Execute unit tests with:

```bash
mvn test
```

Note: the build requires access to Maven Central to download dependencies.

## Development Notes
`UHFReaderController` references a `DepartmentService` that is not injected, which will cause a build error until fixed.
