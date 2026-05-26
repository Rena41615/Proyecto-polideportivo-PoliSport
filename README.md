<div align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3.2"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL 8.0"/>
  <img src="https://img.shields.io/badge/Docker-compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Compose"/>
  <img src="https://img.shields.io/badge/GraalVM-Native-FF8C00?style=for-the-badge&logo=graalvm&logoColor=white" alt="GraalVM"/>
</div>

<h1 align="center">🏟️ Polisport</h1>

<p align="center">
  <strong>Plataforma integral de gestion deportiva</strong><br>
  Arquitectura de microservicios para la administracion de atletas, entrenamientos, nutricion, salud, competencias y mas.
</p>

<p align="center">
  <b>Tech stack:</b> Java 17 · Spring Boot 3.2 · MySQL 8.0 · Docker · Flyway · Resilience4j
</p>

---

## 📋 Tabla de Contenidos

- [Arquitectura](#arquitectura)
- [Microservicios](#microservicios)
  - [Core](#-core)
  - [Gestion](#-gestion)
  - [Soporte](#-soporte)
- [Requisitos](#-requisitos)
- [Inicio Rapido](#-inicio-rapido)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Tecnologias](#-tecnologias)

---

## 🏗️ Arquitectura

Polisport esta construido bajo una **arquitectura de microservicios**, donde cada dominio de negocio es independiente con su propia base de datos MySQL y API REST. La comunicacion entre servicios se realiza mediante **WebClient reactivo** con **circuit breakers** (Resilience4j) para tolerancia a fallos.

```
┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│  Atletas    │  │  Biometria  │  │  Salud      │
│  :8081      │  │  :8082      │  │  :8089      │
└──────┬──────┘  └──────┬──────┘  └──────┬──────┘
       │                 │               │
       └─────────────────┼───────────────┘
                         │
                ┌────────┴────────┐
                │   WebClient     │
                │  (Reactive)     │
                └────────┬────────┘
                         │
       ┌─────────────────┼─────────────────┐
       │                 │                 │
┌──────┴──────┐  ┌──────┴──────┐  ┌──────┴──────┐
│Competencias │  │Entrenamiento│  │  Staff      │
│  :8083      │  │  :8085      │  │  :8088      │
└─────────────┘  └─────────────┘  └─────────────┘
```

---

## 🧩 Microservicios

### 🎯 Core

| Servicio | Puerto | Descripcion |
|----------|--------|-------------|
| **Atletas** | `8081` | Gestion de atletas: RUN, nombres, datos personales, historial deportivo |
| **Biometria** | `8082` | Analisis biometricos: peso, altura, IMC, VO2 max, frecuencia cardiaca |
| **Competencia** | `8083` | Competencias deportivas: categorias, modalidades, resultados |
| **Entrenamiento** | `8085` | Sesiones de entrenamiento: tipo, duracion, participantes |
| **Salud** | `8089` | Gestion medica: lesiones, tratamientos, historial clinico |

### 📋 Gestion

| Servicio | Puerto | Descripcion |
|----------|--------|-------------|
| **Contratos** | `8084` | Administracion de contratos laborales del personal |
| **Inventario** | `8086` | Control de inventario e instalaciones deportivas |
| **Staff** | `8088` | Gestion de miembros del staff, roles y permisos |

### 🔐 Soporte

| Servicio | Puerto | Descripcion |
|----------|--------|-------------|
| **IAM** | `8087` | Identidad y acceso: usuarios, roles, permisos |
| **Nutricion** | `8088` | Planes nutricionales, pautas alimentarias, suplementacion |

---

## 📋 Requisitos

- **Java 17** o superior
- **Maven 3.8+** (o usar `mvnw` incluido)
- **Docker** y **Docker Compose**
- Puerto 3306 y 8080-8089 disponibles

---

## 🚀 Inicio Rapido

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/polisport.git
cd polisport
```

### 2. Levantar las bases de datos con Docker

```bash
docker compose up -d
```

Esto levantara 9 instancias de MySQL 8.0, una por microservicio.

### 3. Compilar y ejecutar

```bash
# Compilar todo el proyecto
./mvnw clean install -DskipTests

# Ejecutar un servicio especifico (ej: atletas)
./mvnw spring-boot:run -pl atletas-service
```

O desde tu IDE favorito (IntelliJ IDEA recomendado), ejecutar cada servicio individualmente.

---

## 📁 Estructura del Proyecto

```
polisport/
├── pom.xml                          # POM padre multi-modulo
├── docker-compose.yml               # Orquestacion Docker
│
├── atletas-service/                 # 🎯 Gestion de atletas
│   ├── controller/                  #   REST controllers
│   ├── service/                     #   Logica de negocio
│   ├── repository/                  #   Acceso a datos
│   ├── model/                       #   Entidades JPA
│   ├── client/                      #   Cliente HTTP reactivo
│   └── config/                      #   Configuraciones
│
├── biometria-service/               # 📊 Analisis biometricos
├── competencia-service/             # 🏆 Competencias
├── contratos-service/               # 📄 Contratos
├── entrenamiento-service/           # 🏋️ Entrenamiento
├── iam-service/                     # 🔐 Identidad y acceso
├── inventario-service/              # 📦 Inventario
├── nutricion-service/               # 🥗 Nutricion
├── salud-service/                   # 🏥 Salud
└── staff-service/                   # 👥 Staff
```

Cada microservicio sigue la misma estructura interna:

```
└── src/main/java/com/polisport/<servicio>/
    ├── <Servicio>Application.java    # Punto de entrada
    ├── controller/                   # Capa REST
    ├── service/                      # Logica de negocio
    ├── repository/                   # Acceso a datos (JPA)
    ├── model/                        # Entidades y enums
    ├── client/                       # Comunicacion entre servicios
    ├── config/                       # Configuracion (WebClient, etc.)
    └── exception/                    # Manejo global de errores
```

---

## 🛠️ Tecnologias

| Tecnologia | Version | Proposito |
|------------|---------|-----------|
| Java | 17 | Lenguaje base |
| Spring Boot | 3.2.0 | Framework principal |
| Spring Data JPA | 3.2.0 | Persistencia y ORM |
| Spring WebFlux | 3.2.0 | Cliente HTTP reactivo |
| Spring Cloud | 2023.0.0 | Circuit breaker (Resilience4j) |
| MySQL | 8.0 | Base de datos relacional |
| Flyway | - | Migraciones de base de datos |
| Lombok | - | Reduccion de boilerplate |
| Jakarta Validation | - | Validacion de datos |
| Docker | - | Contenedores |
| GraalVM | - | Compilacion nativa |

---

## 🌐 Endpoints

Cada servicio expone su API REST en `http://localhost:<puerto>/api/<recurso>`.

Ejemplo con el servicio de atletas:

```
GET    /api/atletas              # Listar todos los atletas
GET    /api/atletas/{id}         # Obtener atleta por ID
GET    /api/atletas/run/{run}    # Obtener atleta por RUN
POST   /api/atletas              # Crear atleta
PUT    /api/atletas/{id}         # Actualizar atleta
DELETE /api/atletas/{id}         # Eliminar atleta
```

---

<div align="center">
  <sub>Hecho con ❤️ para la gestion deportiva</sub>
</div>
