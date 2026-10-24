#!/usr/bin/env bash
# ======================================================================
#           📦 NorthwindDemoAccessData - Ejecución Automática (Mac/Linux)
#         Generado por Agent-Nova-Gemini-Assistant para Alexander
# ======================================================================

set -e

echo "======================================================================"
echo "          📦 NorthwindDemoAccessData - Ejecución Automática"
echo "        Generado por Agent-Nova-Gemini-Assistant para Alexander"
echo "======================================================================"
echo ""

# 1. Comprobar si mysql CLI está disponible para chequear/crear la BD
if command -v mysql &> /dev/null; then
    echo "[INFO] Verificando base de datos 'almacen' en MariaDB / MySQL..."
    if ! mysql -u root -e "USE almacen;" 2>/dev/null; then
        echo "[INFO] Importando src/main/java/database/schema.sql..."
        mysql -u root < "src/main/java/database/schema.sql"
        echo "[OK] Base de datos 'almacen' creada con éxito."
    else
        echo "[OK] Base de datos 'almacen' ya existe y está lista."
    fi
else
    echo "[AVISO] 'mysql' CLI no encontrado en PATH. Asegúrate de que MySQL/MariaDB esté iniciado en el puerto 3306."
fi

echo ""
echo "[SISTEMA] Compilando y ejecutando con Maven..."
echo "----------------------------------------------------------------------"

mvn clean compile exec:java -Dexec.mainClass="Main"

echo ""
echo "======================================================================"
echo "[FIN] Ejecución finalizada con éxito."
echo "======================================================================"
