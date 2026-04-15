@echo off
setlocal

set "ANTLR_JAR=C:\antlr\antlr-4.13.2-complete.jar"
set "PAUSE_ON_EXIT=1"
set "EXIT_CODE=0"

if /I "%~1"=="--no-pause" (
    set "PAUSE_ON_EXIT=0"
    shift
)

if not exist "%ANTLR_JAR%" (
    echo [ERROR] No se encontro ANTLR en: %ANTLR_JAR%
    echo Edita ejecutar.bat y cambia la ruta de ANTLR_JAR.
    set "EXIT_CODE=1"
    goto fin
)

echo Compilando fuentes Java...
javac -cp ".;%ANTLR_JAR%" *.java
if errorlevel 1 (
    echo [ERROR] Fallo la compilacion.
    set "EXIT_CODE=1"
    goto fin
)

if /I "%~1"=="--stdin" goto stdin
if "%~1"=="" goto pedir_archivo

echo Ejecutando con argumentos: %*
java -cp ".;%ANTLR_JAR%" main %*
set "EXIT_CODE=%errorlevel%"
goto fin

:pedir_archivo
set "INPUT_FILE="
set /p INPUT_FILE=Ingresa la ruta del archivo .txt a analizar: 

if "%INPUT_FILE%"=="" (
    echo [ERROR] No ingresaste una ruta de archivo.
    set "EXIT_CODE=1"
    goto fin
)

if not exist "%INPUT_FILE%" (
    echo [ERROR] El archivo no existe: %INPUT_FILE%
    set "EXIT_CODE=1"
    goto fin
)

for %%I in ("%INPUT_FILE%") do set "EXT=%%~xI"
if /I not "%EXT%"==".txt" (
    echo [AVISO] El archivo no tiene extension .txt, se intentara analizar igual.
)

set "RUN_PARSE="
set /p RUN_PARSE=Quieres ejecutar tambien el parser? (s/n): 

if /I "%RUN_PARSE%"=="s" (
    java -cp ".;%ANTLR_JAR%" main "%INPUT_FILE%" --parse
) else (
    java -cp ".;%ANTLR_JAR%" main "%INPUT_FILE%"
)
set "EXIT_CODE=%errorlevel%"
goto fin

:stdin
echo Ejecutando desde teclado (Ctrl+Z y Enter para finalizar)...
java -cp ".;%ANTLR_JAR%" main
set "EXIT_CODE=%errorlevel%"

:fin
if "%PAUSE_ON_EXIT%"=="1" pause
exit /b %EXIT_CODE%
