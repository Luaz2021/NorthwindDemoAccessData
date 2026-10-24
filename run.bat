@echo off
chcp 65001 > nul
title NorthwindDemoAccessData - Ejecución Automática

echo ======================================================================
echo           📦 NorthwindDemoAccessData - Ejecución Automática
echo         Generado por Agent-Nova-Gemini-Assistant para Alexander
echo ======================================================================
echo.

:: 1. Verificar si MySQL CLI está disponible para importar schema si hace falta
where mysql >nul 2>nul
if %errorlevel% equ 0 (
    echo [INFO] Verificando base de datos en MariaDB / MySQL...
    mysql -u root -e "USE almacen;" >nul 2>nul
    if %errorlevel% neq 0 (
        echo [INFO] Base de datos 'almacen' no encontrada. Importando schema.sql...
        mysql -u root < "src\main\java\database\schema.sql"
        if %errorlevel% equ 0 (
            echo [OK] Base de datos 'almacen' creada e inicializada con éxito.
        ) else (
            echo [AVISO] No se pudo inicializar la BD automáticamente. Asegúrate de iniciar Laragon/MySQL.
        )
    ) else (
        echo [OK] Base de datos 'almacen' detectada y lista.
    )
) else (
    echo [AVISO] 'mysql' CLI no está en el PATH global (Laragon maneja su propio MySQL).
    echo [INFO] Asegúrate de que Laragon/MySQL esté INICIADO en el puerto 3306.
)

echo.
echo [SISTEMA] Compilando y ejecutando con Maven...
echo ----------------------------------------------------------------------

call mvn clean compile exec:java -Dexec.mainClass="Main"

echo.
echo ======================================================================
echo [FIN] Ejecución finalizada.
echo ======================================================================
pause
