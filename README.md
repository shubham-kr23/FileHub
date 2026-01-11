# FileHub
Inspired by Dropbox

### Owner
```
Name: Shubham Kumar Mandal 
Email: shubhamkrm23@gmail.com
```
### Few features are yet to implemented
 - Integrating with s3, for now using local storage
 - adding DB

### Use script to run:
```
./start.sh help         
Usage: ./start.sh <start|restart|stop|status|help>

Commands:
  start   Start backend and frontend
  restart Restart backend and frontend
  stop    Stop backend and frontend
  status  Show running status
  help    Show this message
```

### Running locally ⚙️

For development you can use the local profile which uses an in-memory H2 database and local file storage:

```bash
# run with the 'local' Spring profile (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Flyway is included and will run migrations on startup to create the required tables (e.g. `file_metadata`).

---

