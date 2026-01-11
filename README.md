# FileHub
Inspired by Dropbox

### Owner
```
Name: Shubham Kumar Mandal 
Email: shubhamkrm23@gmail.com
```
### Few features are yet to implemented

### Use script to run:
```
./start.sh help         
Usage: ./start.sh <start|stop|status|help>

Commands:
  start   Start backend and frontend
  stop    Stop backend and frontend
  status  Show running status
  help    Show this message
```

## Secrets (example)

This directory is git-ignored to avoid committing real credentials. It includes an example file `credentials.example.json` that you can copy to `credentials.json` and populate with your real values.

Usage

1. Copy the example:

   ```bash
   cp secrets/credentials.example.json secrets/credentials.json
   # then edit secrets/credentials.json to add real values
   ```

2. The backend can read these values via your own startup script, environment variables, or a small config loader (not included by default).


### Running locally ⚙️

For development you can use the local profile which uses an in-memory H2 database and local file storage:

```bash
# run with the 'local' Spring profile (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=local
# or
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Or use Docker + Postgres (recommended for integration-like local testing):

```bash
# start Postgres and Adminer
docker compose up -d

# start the app with the 'local' profile which is configured to talk to the postgres service
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Flyway is included and will run migrations on startup to create the required tables (e.g. `file_metadata`). If you prefer Postgres on host instead of Docker, ensure a database named `filestorage` exists and credentials in `application.yml` are correct. With `ddl-auto: validate` Hibernate will expect the schema to be present; Flyway creates the schema for us in the Docker setup above.

---

