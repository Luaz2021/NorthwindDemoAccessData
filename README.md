# 📦 NorthwindDemoAccessData

> Sistema integral de persistencia JDBC, sincronización con API REST y consultas relacionales avanzadas en Java 21 sobre MariaDB / MySQL.

[![Java 21](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)](https://mariadb.org/)
[![Laragon](https://img.shields.io/badge/Laragon-0E7BEA?style=for-the-badge&logo=laragon&logoColor=white)](https://laragon.org/)
[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/)
[![Apache Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Google Gemini](https://img.shields.io/badge/Tested_with-Google_Gemini-8E75C4?style=for-the-badge&logo=google-gemini&logoColor=white)](https://gemini.google.com/)

---

## 📋 Descripción del Proyecto

Proyecto desarrollado para la asignatura de **Acceso a Datos** del ciclo formativo de grado superior en **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

El objetivo central es implementar un ecosistema robusto de persistencia y manipulación de datos relacionales contra un SGBD **MySQL / MariaDB** utilizando exclusivamente **drivers JDBC nativos**, integrando servicios externos y cumpliendo al 100% con los requerimientos técnicos y arquitectónicos solicitados:

* 🔌 **Conectividad JDBC & Patrón Singleton:** Gestión centralizada de la base de datos a través de `DBConnection`, asegurando una única conexión activa reutilizable, control de reconexión automática y encapsulamiento privado.
* 🌐 **Ingestión de Datos vía API REST:** Conexión nativa HTTPS (`HttpURLConnection`) contra el endpoint de DummyJSON (`https://dummyjson.com/products`), lectura fluida con `BufferedReader` y procesamiento de arrays con `org.json`.
* ⚡ **Persistencia Masiva Eficiente (Batch):** Inserción por lotes de 30 productos con `PreparedStatement.addBatch()` y `executeBatch()`, optimizando el rendimiento y evitando saturar el servidor con sentencias individuales.
* ✍️ **Inserción Directa con Statement:** Creación e inserción obligatoria mediante objetos `Statement` de una serie manual de entidades dependientes (empleados y pedidos vinculados con integridad referencial).
* ⭐ **Inserción Condicional en Favoritos:** Detección y filtrado de productos con precio superior a **1000 €**, volcados a la tabla `productos_fav` con `Statement` mediante una subconsulta relacional (`INSERT ... SELECT`).
* 🔍 **Consultas Relacionales con Statement & ResultSet:**
  1. **Catálogo Completo:** Recuperación y lectura de todos los productos del almacén.
  2. **Favoritos con INNER JOIN:** Cruce relacional entre `productos` y `productos_fav` para mostrar nombre, precio y stock de los favoritos.
  3. **Filtro de Oportunidades:** Extracción condicional de productos económicos con precio inferior a **600 €**.
* 📊 **Renderizado Tabular:** Salida visual de tablas formateadas por consola (`Main.java`), diseñada para verificación rápida en demostraciones y defensas técnicas.

---

## 📂 Estructura del Proyecto

```text
NorthwindDemoAccessData/
├── src/main/java/
│   ├── database/
│   │   ├── DBConnection.java     # Singleton JDBC con reconexión
│   │   ├── SQLQueries.java       # Centralización limpia de sentencias SQL
│   │   └── schema.sql            # Script DDL completo (Tablas y Foreign Keys)
│   ├── model/
│   │   ├── Empleados.java        # Modelo Lombok para empleados
│   │   ├── Pedidos.java          # Modelo Lombok para pedidos (FKs)
│   │   ├── Productos.java        # Modelo Lombok de productos base
│   │   └── ProductosFav.java     # Modelo Lombok de productos favoritos
│   ├── repository/
│   └── AlmacenRepository.java # Operaciones SQL: Batch, Statements y ResultSets
│   ├── service/
│   │   ├── AlmacenService.java    # Orquestador del flujo de negocio y persistencia
│   │   └── DummyJsonService.java  # Cliente HTTP nativo y parser de DummyJSON
│   └── Main.java                 # Entrypoint y formateo de tablas en consola
├── pom.xml                       # Configuración Maven (Java 21, Lombok, MySQL, org.json)
├── run.bat                       # Script de arranque automático para Windows
├── run.sh                        # Script de arranque automático para Mac / Linux
└── README.md                     # Documentación técnica oficial
```

---

## 🛠️ Entorno de Desarrollo & Herramientas

* **IDE:** IntelliJ IDEA 2024 (soporte completo de anotaciones Lombok y compilador Java 21).
* **Servidor Local / SGBD:** Laragon con MariaDB (puerto `3306`, base de datos `almacen`, usuario `root`, password vacía).
* **Runtime:** Java OpenJDK 21.
* **Build System:** Apache Maven 3.8+.
* **Librerías Clave:** Project Lombok (1.18.34) para código limpio y `org.json` (20240303) para parseo de datos web.
* **Testing, Depuración & QA:** Verificación estática, erradicación de warnings y auditoría de calidad asistida con **Google Gemini** (semáforo en verde completo).

---

## 🚀 Instalación y Puesta en Marcha

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Luaz2021/NorthwindDemoAccessData.git
cd NorthwindDemoAccessData
```

### 2. Base de Datos (Laragon / MariaDB / MySQL)
Asegúrate de que tu servidor MySQL/MariaDB esté corriendo en el puerto `3306`.

El esquema completo se encuentra en `src/main/java/database/schema.sql`.

* **Vía phpMyAdmin / HeidiSQL (Laragon):**
  Abre `src/main/java/database/schema.sql` y ejecútalo con un clic.
* **Vía Terminal (Windows / Mac / Linux):**
  ```bash
  mysql -u root < src/main/java/database/schema.sql
  ```

### 3. Ejecución

* **💻 Opción A — Desde cualquier IDE (IntelliJ, Eclipse, VS Code):**
  Abre la carpeta del proyecto. El IDE leerá el `pom.xml`, descargará todas las dependencias automáticamente y solo debes pulsar **Run** en `Main.java`.

* **⚡ Opción B — Script Automático en Windows:**
  Doble clic sobre `run.bat` o desde la terminal:
  ```cmd
  run.bat
  ```

* **🍎 Opción C — Script Automático en Mac / Linux:**
  ```bash
  chmod +x run.sh
  ./run.sh
  ```

* **☕ Opción D — Vía Maven CLI:**
  ```bash
  mvn clean compile exec:java -Dexec.mainClass="Main"
  ```

---

## 🔄 Pipeline de Datos y Flujo Arquitectónico

```text
 ┌──────────────────────┐
 │   API de DummyJSON   │  (https://dummyjson.com/products)
 └──────────┬───────────┘
            │  HTTPS GET / Stream
            ▼
 ┌──────────────────────┐
 │   DummyJsonService   │  (HttpURLConnection + BufferedReader + org.json)
 └──────────┬───────────┘
            │  List<Productos>
            ▼
 ┌──────────────────────┐
 │    AlmacenService    │  (Orquestación de Persistencia y Consultas)
 └──────────┬───────────┘
            │
            ├─────► PreparedStatement Batch ──────► [ Tabla: productos ]
            │
            ├─────► Statement (Insert Manual) ────► [ Tablas: empleados & pedidos ]
            │
            ├─────► Statement + Subquery ─────────► [ Tabla: productos_fav (> 1000 €) ]
            │
            └─────► Consultas Relacionales (Statement & ResultSets)
                    ├── 1. Catálogo Completo
                    ├── 2. Favoritos con INNER JOIN
                    └── 3. Productos Menores a 600 EUR
                            │
                            ▼
                    ┌───────────────┐
                    │   Main.java   │ ──► Salida limpia y formateada en Consola 🖥️
                    └───────────────┘
```

---

## 🤖 Créditos & Asistencia Técnica

* **Desarrollo & Arquitectura:** Alexander Martínez (Saúl Martínez)
* **Estructura y Documentación README:** Generado por **Agent-Nova-Gemini-Assistant** ✨ (Copiloto y Asistente Técnico Autónomo basado en Google Gemini).
* **Automatización y Scripts Multiplataforma (`run.bat` & `run.sh`):** Diseñados y generados por **Agent-Nova-Gemini-Assistant**.
