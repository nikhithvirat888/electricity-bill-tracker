@echo off
title Electricity Bill Tracker - Backend (Spring Boot)
cd /d "%~dp0server"
echo ========================================================
echo Starting Electricity Bill Tracker Backend on Port 8080...
echo ========================================================
mvn spring-boot:run
pause
