<div align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3.2"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL 8.0"/>
  <img src="https://img.shields.io/badge/Docker-compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Compose"/>
  <img src="https://img.shields.io/badge/API_Gateway-SpringCloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Cloud Gateway"/>
  <img src="https://img.shields.io/badge/JaCoCo-0.8.12-FF0000?style=for-the-badge" alt="JaCoCo Coverage"/>
</div>

<h1 align="center"> PoliSport</h1>

<p align="center">
  <strong>Plataforma integral de gestion deportiva</strong><br>
  Arquitectura de microservicios para la administracion de atletas, entrenamientos, nutricion, salud, competencias y mas.
</p>

---

## Estrategia de Branching — GitHub Flow

Este proyecto sigue **GitHub Flow** como estrategia de branching:

```
main ──── merge ──── merge ──── merge ────
          ↑           ↑           ↑
          |           |           |
feature/  feature/   feature/   hotfix/
atletas   gateway    testing    login-bug
```

### Reglas:
- **main**: siempre desplegable, codigo estable y probado
- **feature/***: ramas para nuevas funcionalidades (ej: `feature/atletas-crud`)
- **hotfix/***: ramas para correcciones urgentes en produccion
- **No hay rama develop**: se integra directamente a main via Pull Request
- Cada PR debe pasar los tests y tener al menos 1 approval

---

## Arquitectura

```
                          ┌─────────────────┐
                          │   Gateway       │
                          │   :8080         │
                          └────────┬────────┘
                                   │
              ┌────────────────────┼────────────────────┐
              │                    │                    │
     ┌────────┴────────┐  ┌───────┴────────┐  ┌───────┴────────┐
     │   Atletas       │  │  Biometria     │  │  Competencias  │
     │   :8081         │  │  :8082         │  │  :8083         │
     └────────┬────────┘  └───────┬────────┘  └───────┬────────┘
              │                    │                    │
     ┌────────┴────────┐  ┌───────┴────────┐  ┌───────┴────────┐
     │   Contratos     │  │  Entrenamiento │  │  Inventario    │
     │   :8084         │  │  :8085         │  │  :8086         │
     └────────┬────────┘  └───────┬────────┘  └───────┬────────┘
              │                    │                    │
     ┌────────┴────────┐  ┌───────┴────────┐  ┌───────┴────────┐
     │   Salud         │  │  IAM           │  │  Nutricion     │
     │   :8087         │  │  :8088         │  │  :8089         │
     └────────┬────────┘  └───────┬────────┘  └───────┬────────┘
              │                    │                    │
              └────────────────────┼────────────────────┘
                                   │
                          ┌────────┴────────┐
                          │   Staff         │
                          │   :8090         │
                          └─────────────────┘
```

Cada microservicio tiene su propia base de datos MySQL 8.0 independiente con migraciones Flyway.

---

## Microservicios y Swagger

| # | Servicio | Puerto | Swagger UI | API Docs |
|---|----------|--------|------------|----------|
| 1 | **Atletas** | `8081` | http://localhost:8081/swagger-ui.html | http://localhost:8081/api-docs |
| 3 | **Biometria** | `8082` | http://localhost:8082/swagger-ui.html | http://localhost:8082/api-docs |
| 4 | **Competencias** | `8083` | http://localhost:8083/swagger-ui.html | http://localhost:8083/api-docs |
| 5 | **Contratos** | `8084` | http://localhost:8084/swagger-ui.html | http://localhost:8084/api-docs |
| 6 | **Entrenamientos** | `8085` | http://localhost:8085/swagger-ui.html | http://localhost:8085/api-docs |
| 7 | **Inventario** | `8086` | http://localhost:8086/swagger-ui.html | http://localhost:8086/api-docs |
| 8 | **Salud** | `8087` | http://localhost:8087/swagger-ui.html | http://localhost:8087/api-docs |
| 9 | **IAM** | `8088` | http://localhost:8088/swagger-ui.html | http://localhost:8088/api-docs |
| 10 | **Nutricion** | `8089` | http://localhost:8089/swagger-ui.html | http://localhost:8089/api-docs |
| 11 | **Staff** | `8090` | http://localhost:8090/swagger-ui.html | http://localhost:8090/api-docs |

> Tambien puedes acceder a todos los servicios a traves del Gateway en `http://localhost:8080/<ruta-del-servicio>`

---

## Endpoints por servicio

### Gateway (API Gateway)
```
GET  /api/atletas/**        → atletas-service:8081
GET  /api/v1/biometria/**   → biometria-service:8082
GET  /api/competencias/**   → competencia-service:8083
GET  /api/contratos/**      → contratos-service:8084
GET  /api/entrenamientos/** → entrenamiento-service:8085
GET  /api/v1/inventario/**  → inventario-service:8086
GET  /api/v1/instalaciones/** → inventario-service:8086
GET  /api/v1/salud/**       → salud-service:8087
GET  /api/v1/iam/**         → iam-service:8088
GET  /api/v1/nutricion/**   → nutricion-service:8089
GET  /api/v1/staff/**       → staff-service:8090
```

### Atletas (`/api/atletas`)
```
GET    /                  Listar todos
GET    /{id}              Obtener por ID
GET    /run/{run}         Obtener por RUN
POST   /                  Crear
PUT    /{id}              Actualizar
PATCH  /{id}              Actualizacion parcial
DELETE /{id}              Eliminar
```

### Biometria (`/api/v1/biometria`)
```
GET    /                  Listar
GET    /{id}              Obtener por ID
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Competencias (`/api/competencias`)
```
GET    /                  Listar
GET    /{id}              Obtener por ID
GET    /atleta/{run}      Listar por atleta
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Contratos (`/api/contratos`)
```
GET    /                  Listar
GET    /{id}              Obtener por ID
GET    /empleado/{run}    Listar por empleado
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Entrenamientos (`/api/entrenamientos`)
```
GET    /                  Listar
GET    /{id}              Obtener por ID
GET    /atleta/{run}      Listar por atleta
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### IAM (`/api/v1/iam`)
```
GET    /usuarios              Listar usuarios
GET    /usuarios/{id}         Obtener usuario
POST   /usuarios              Crear usuario
PUT    /usuarios/{id}         Actualizar usuario
DELETE /usuarios/{id}         Eliminar usuario
GET    /roles                 Listar roles
GET    /roles/{id}            Obtener rol
POST   /roles                 Crear rol
PUT    /roles/{id}            Actualizar rol
DELETE /roles/{id}            Eliminar rol
GET    /permisos              Listar permisos
POST   /permisos              Crear permiso
GET    /roles-usuarios        Listar asignaciones
POST   /roles-usuarios        Asignar rol a usuario
GET    /permisos-rol          Listar permisos por rol
POST   /permisos-rol          Asignar permiso a rol
```

### Inventario (`/api/v1/inventario`)
```
GET    /                  Listar
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Instalaciones (`/api/v1/instalaciones`)
```
GET    /                  Listar
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Nutricion (`/api/v1/nutricion`)
```
GET    /                  Listar planes
GET    /{id}              Obtener por ID
GET    /atleta/{id}       Buscar por atleta
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Salud (`/api/v1/salud`)
```
GET    /                  Listar registros
GET    /{id}              Obtener por ID
GET    /atleta/{id}       Buscar por atleta
POST   /                  Crear
PUT    /{id}              Actualizar
DELETE /{id}              Eliminar
```

### Staff (`/api/v1/staff`)
```
GET    /                  Listar miembros
GET    /roles             Listar roles
POST   /                  Crear miembro
POST   /roles             Crear rol
```

---

## Requisitos

- **Java 17** o superior
- **Maven 3.8+** (o usar `mvnw` incluido)
- **Docker** y **Docker Compose** (para base de datos)
- Puertos 8080-8090 disponibles

---

## Inicio Rapido

### 1. Clonar e iniciar bases de datos

```bash
git clone https://github.com/tu-usuario/polisport.git
cd polisport
docker compose up -d
```

Esto levantara 10 instancias de MySQL 8.0, una por microservicio.

### 2. Compilar y ejecutar

```bash
# Compilar todo el proyecto
./mvnw clean install

# Ejecutar todos los servicios via Docker
docker compose up --build

# O ejecutar servicios individualmente
./mvnw spring-boot:run -pl gateway-service
./mvnw spring-boot:run -pl atletas-service
```

### 3. Probar

```bash
# Via Gateway (recomendado)
curl http://localhost:8080/api/atletas

# Via servicio directo
curl http://localhost:8081/api/atletas

# Swagger UI
open http://localhost:8080/swagger-ui.html
```

---

## Pruebas y Cobertura

```bash
# Ejecutar todos los tests
./mvnw test

# Reporte de cobertura JaCoCo
open target/site/jacoco/index.html
```

Los tests incluyen:
- Tests de contexto Spring Boot
- Tests unitarios con Mockito para servicios clave
- Cobertura de codigo via JaCoCo

---

## Despliegue

### Local con Docker
```bash
docker compose up --build
```

### Render (Cloud)
El archivo `render.yaml` contiene la configuracion para desplegar los 11 servicios en Render.
```bash
# Usar Blueprint de Render con render.yaml
# Conectar repositorio a Render y seleccionar "Blueprint"
```

---

## ClickUp

Este proyecto utiliza ClickUp para la gestion de tareas.
El archivo `.clickup/tasks.json` contiene la lista completa de tareas del proyecto.

Panel: Crear un nuevo Space en ClickUp e importar las tareas desde `.clickup/tasks.json`

---

## Postman

La coleccion de Postman se encuentra en:
```
postman/polisport-collection.json
```

Importar en Postman: `File → Import → Seleccionar archivo`

Variable de entorno configurada:
- `baseUrl`: `http://localhost:8080` (Gateway)

---

## Tecnologias

| Tecnologia | Version | Proposito |
|------------|---------|-----------|
| Java | 17 | Lenguaje base |
| Spring Boot | 3.2.0 | Framework principal |
| Spring Cloud Gateway | 2023.0.0 | API Gateway |
| Spring Data JPA | 3.2.0 | Persistencia y ORM |
| Spring WebFlux | 3.2.0 | Cliente HTTP reactivo |
| Spring Cloud Gateway | 2023.0.0 | Circuit breaker |
| MySQL | 8.0 | Base de datos relacional |
| Flyway | - | Migraciones de base de datos |
| Lombok | - | Reduccion de boilerplate |
| Jakarta Validation | - | Validacion de datos |
| Docker | - | Contenedores |
| JaCoCo | 0.8.12 | Cobertura de codigo |
| GraalVM | - | Compilacion nativa |

---

## ClickUp Tasks Quick Reference

| ID | Tarea | Estado |
|----|-------|--------|
| POLISPORT-1 | Infraestructura Docker | Done |
| POLISPORT-2 | API Gateway | Done |
| POLISPORT-3 | CRUD Atletas | Done |
| POLISPORT-4 | CRUD Biometria | Done |
| POLISPORT-5 | CRUD Competencias | Done |
| POLISPORT-6 | CRUD Contratos | Done |
| POLISPORT-7 | CRUD Entrenamientos | Done |
| POLISPORT-8 | IAM | Done |
| POLISPORT-9 | Inventario | Done |
| POLISPORT-10 | Nutricion | Done |
| POLISPORT-11 | Salud | Done |
| POLISPORT-12 | Staff | Done |
| POLISPORT-13 | Pruebas + JaCoCo | Done |
| POLISPORT-14 | Despliegue Render | Done |
| POLISPORT-15 | Postman Collection | Done |
| POLISPORT-16 | Documentacion | Done |
