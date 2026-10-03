@echo off
title Electricity Bill Tracker Launcher
echo ========================================================
echo Launching Electricity Bill & Usage Tracker...
echo ========================================================

start "Backend Server (Spring Boot)" cmd /k "cd /d %~dp0server && mvn spring-boot:run"
start "Frontend App (React Vite)" cmd /k "cd /d %~dp0client && npm run dev"

echo.
echo Both servers have been launched in separate windows!
echo Waiting a few seconds for servers to initialize...
timeout /t 5 /nobreak >nul

start http://localhost:5173
echo Application opened in browser: http://localhost:5173
