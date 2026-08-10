@echo off
REM Rokomari Application Launcher
REM This script starts PostgreSQL and runs the Spring Boot application

echo ========================================
echo  Rokomari - Spring Boot Application
echo ========================================
echo.

REM Add PostgreSQL to PATH
set PATH=%PATH%;C:\Program Files\PostgreSQL\18\bin

REM Check PostgreSQL service status
echo Checking PostgreSQL service...
sc query postgresql-x64-18 | find "RUNNING" >nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] PostgreSQL is running
) else (
    echo [INFO] PostgreSQL is not running
    echo Attempting to start PostgreSQL (may require admin privileges)...
    net start postgresql-x64-18 >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo [OK] PostgreSQL started successfully
        timeout /t 3 /nobreak
    ) else (
        echo [WARNING] Could not start PostgreSQL service
        echo Please ensure PostgreSQL 18 is installed and running
        echo You may need to start it manually using Windows Services (services.msc)
    )
)

echo.
echo Checking database connection...
set PGPASSWORD=password
psql -U postgres -h localhost -c "SELECT 1;" >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [OK] PostgreSQL is accessible
    
    REM Create database if it doesn't exist
    echo Creating database if not exists...
    psql -U postgres -h localhost -c "CREATE DATABASE rokomari;" 2>nul
    echo [OK] Database ready
) else (
    echo [ERROR] Cannot connect to PostgreSQL
    echo Please ensure PostgreSQL is running and accessible on localhost:5432
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Starting Rokomari Application...
echo ========================================
echo.

cd /d "%~dp0rokomari"
if not exist "target\rokomari-0.0.1-SNAPSHOT.jar" (
    echo Error: JAR file not found!
    echo Please run: mvnw clean package -DskipTests
    pause
    exit /b 1
)

echo Application will run on: http://localhost:8080
echo.
java -jar target\rokomari-0.0.1-SNAPSHOT.jar

pause
