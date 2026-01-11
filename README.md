# FileHub
Inspired by Dropbox

### Owner
```
Name: Shubham Kumar Mandal 
Email: shubhamkrm23@gmail.com
```
### Few features are yet to implemented
 - Integrating with s3, for now using local storage
 - Configure DB 

### Home Page UI
<img width="1463" height="823" alt="Screenshot 2026-01-12 at 12 04 03 AM" src="https://github.com/user-attachments/assets/b36f0695-ab64-422b-bdba-06a4af77b7a9" />

### This is how Download work
<img width="1470" height="956" alt="Screenshot 2026-01-12 at 12 06 44 AM" src="https://github.com/user-attachments/assets/72b7d88f-d5b8-45a4-a474-574b5b19caa5" />

### Delet All Files from Uploaded list
<img width="1470" height="956" alt="Screenshot 2026-01-12 at 12 08 03 AM" src="https://github.com/user-attachments/assets/bfbef806-3ee0-44b9-bae1-ed95ab28e9b0" />
<img width="1470" height="956" alt="Screenshot 2026-01-12 at 12 08 13 AM" src="https://github.com/user-attachments/assets/32a71682-591f-4ef7-8945-ff4070685791" />
<img width="1470" height="956" alt="Screenshot 2026-01-12 at 12 08 24 AM" src="https://github.com/user-attachments/assets/e8a61c9e-3136-4321-b89c-f51a9ddb8c6b" />


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
<img width="856" height="484" alt="Screenshot 2026-01-12 at 12 14 28 AM" src="https://github.com/user-attachments/assets/ac454c11-6663-4027-850e-697fc316f331" />



### Running locally ⚙️
#### (Recommended to use script command)

#### Backend server
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

#### Frontend
```bash
npm run dev -- --host
```


Flyway is included and will run migrations on startup to create the required tables (e.g. `file_metadata`).

---

