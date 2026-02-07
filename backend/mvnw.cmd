@echo off
setlocal enabledelayedexpansion

REM Check if Maven is installed globally
where mvn >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo Using system Maven...
    mvn %*
    exit /b %ERRORLEVEL%
)

REM If not, provide helpful message
echo Maven is not installed on this system.
echo Please install Maven 3.9.12 or higher from https://maven.apache.org/download.cgi
echo Or set MAVEN_HOME environment variable to your Maven installation.
exit /b 1
