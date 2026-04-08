@echo off
setlocal

set "ANTLR_JAR=C:\antlr\antlr-4.13.2-complete.jar"

if not exist "%ANTLR_JAR%" (
    echo [ERROR] No se encontro ANTLR en: %ANTLR_JAR%
    echo Edita ejecutar.bat y cambia la ruta de ANTLR_JAR.
    exit /b 1
)

echo Compilando fuentes Java...
javac -cp ".;%ANTLR_JAR%" *.java
if errorlevel 1 (
    echo [ERROR] Fallo la compilacion.
    exit /b 1
)

if "%~1"=="" goto stdin

echo Ejecutando con argumentos: %*
java -cp ".;%ANTLR_JAR%" main %*
exit /b %errorlevel%

:stdin
echo Ejecutando desde teclado (Ctrl+Z y Enter para finalizar)...
java -cp ".;%ANTLR_JAR%" main
exit /b %errorlevel%
