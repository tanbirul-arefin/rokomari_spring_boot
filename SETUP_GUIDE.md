# Rokomari Spring Boot Application - Setup Guide

## ✅ Completed Tasks

1. **Maven Build** - Successfully compiled and packaged your application
   - JAR File: `f:\rokomari\rokomari\target\rokomari-0.0.1-SNAPSHOT.jar` (52 MB)
   - All dependencies downloaded
   - Application ready to run

## 📋 Remaining Steps

### Step 1: Start PostgreSQL (Admin Required)

PostgreSQL 18 is installed but the service is stopped. You need to start it with **Administrator privileges**:

**Option A: Using Windows Services**
1. Press `Win + R` and type: `services.msc`
2. Find `postgresql-x64-18` in the list
3. Right-click and select "Start"
4. Status should change to "Running"

**Option B: Using PowerShell as Administrator**
```powershell
Start-Service postgresql-x64-18
```

### Step 2: Create the Database

Once PostgreSQL is running, open PowerShell and run:

```powershell
$env:PGPASSWORD='password'
$env:PATH += ";C:\Program Files\PostgreSQL\18\bin"
psql -U postgres -h localhost -c "CREATE DATABASE rokomari;"
```

Your app will create the tables automatically when it starts.

### Step 3: Run Your Application

Once PostgreSQL is running, execute:

```powershell
cd f:\rokomari\rokomari
java -jar target\rokomari-0.0.1-SNAPSHOT.jar
```

The application will:
- Connect to PostgreSQL on `localhost:5432`
- Create tables automatically (ddl-auto=update)
- Start on `http://localhost:8080`

### Step 4: Access Your Application

Open your browser and navigate to:
```
http://localhost:8080
```

---

## Database Configuration

Your application is configured with:
- **Database**: rokomari
- **Host**: localhost
- **Port**: 5432
- **Username**: root
- **Password**: 709730
- **Driver**: PostgreSQL JDBC Driver

## Project Structure

- Controllers: Book management API
- Models: Book entity
- Repository: Spring Data JPA
- Service: Business logic layer
- Templates: Thymeleaf HTML templates

---

## Troubleshooting

**PostgreSQL not starting?**
- Ensure you're running PowerShell as Administrator
- Check if port 5432 is not blocked by firewall
- Verify PostgreSQL 18 installation at: `C:\Program Files\PostgreSQL\18`

**JAR file won't run?**
- Ensure Java 17 is installed: `java -version`
- Ensure PostgreSQL is running and database exists
- Check logs for database connection errors

**Port 8080 already in use?**
- Change port in `application.properties`: `server.port=8081`

---

**Your application is ready! Just start PostgreSQL and run the JAR file.**
