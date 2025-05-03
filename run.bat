@echo off
setlocal

echo Compilando...

REM Créer dossier de sortie s'il n'existe pas
if not exist "out" mkdir out

REM Compilation
javac -d out src\main\java\com\p10\App.java

if %errorlevel% neq 0 (
    echo Erreur de compilation.
    pause
    exit /b %errorlevel%
)

echo.
echo Execution:
echo ---------------------------

REM Exécution
java -cp out main.java.com.p10.App

echo.
pause
